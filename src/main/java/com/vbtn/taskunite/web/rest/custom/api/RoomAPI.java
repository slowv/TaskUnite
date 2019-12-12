package com.vbtn.taskunite.web.rest.custom.api;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.AdminProfitRepository;
import com.vbtn.taskunite.repository.MessageRepository;
import com.vbtn.taskunite.repository.ReviewRepository;
import com.vbtn.taskunite.repository.UserInformationRepository;
import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.task.CustomTaskService;
import com.vbtn.taskunite.web.rest.custom.vm.MessageVM;
import com.vbtn.taskunite.web.rest.custom.vm.ReviewVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomAPI {
    @Autowired
    CustomTaskService customTaskService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserInformationRepository userInformationRepository;
    @Autowired
    AdminProfitRepository adminProfitRepository;

    @GetMapping("/{id}/confirm")
    public String confirm(@PathVariable("id") Long id) {
        Task task = customTaskService.findOne(id);
        if (task == null) return null;

        if (task.getStatus() == 0) {
            task.setStatus(1);
            customTaskService.save(task);
        }
        return "true";
    }

    @PostMapping("/{id}/complete")
    public String complete(@PathVariable("id") Long id) {
        Task task = customTaskService.findOne(id);
        if (task == null) return null;


        if (task.getStatus() == 1) {
            task.setTo(Instant.now());
            Duration d = Duration.between(task.getFrom(), Instant.now());
            task.setDuration(d);
            task.setTotalPrice(task.getPrice() * d.toHours());
            task.setStatus(2);

            customTaskService.save(task);
        } else {
            task.setStatus(3);

            customTaskService.save(task);


            UserInformation tasker = task.getTasker();
            UserInformation master = task.getMaster();
            PaymentInformation paymentTasker = tasker.getPayment();
            PaymentInformation paymentMaster = master.getPayment();
            Statistic statisticTasker = tasker.getStatistic();
            Statistic statisticMaster = master.getStatistic();

            paymentMaster.setBalance(paymentMaster.getBalance() - task.getTotalPrice() - (20 - statisticMaster.getLevel()) / 100 * task.getTotalPrice());
            paymentTasker.setBalance(paymentTasker.getBalance() + task.getTotalPrice() - (20 - statisticTasker.getLevel()) / 100 * task.getTotalPrice());

            AdminProfit profit = new AdminProfit();
            profit.setMasterProfit((20 - statisticMaster.getLevel()) / 100 * task.getTotalPrice());
            profit.setTaskerProfit((20 - statisticTasker.getLevel()) / 100 * task.getTotalPrice());
            profit.setTotalProfit((20 - statisticMaster.getLevel()) / 100 * task.getTotalPrice() + (20 - statisticTasker.getLevel()) / 100 * task.getTotalPrice());
            profit.setTask(task);

            adminProfitRepository.save(profit);

            statisticMaster.setExperience(statisticMaster.getExperience() + 20);
            statisticMaster.setCompletedTask(statisticMaster.getCompletedTask() + 1);
            statisticTasker.setExperience(statisticTasker.getExperience() + 20);
            statisticTasker.setCompletedTask(statisticTasker.getCompletedTask() + 1);

            customTaskService.saveAll(task, paymentTasker, statisticTasker, paymentMaster, statisticMaster);
        }
        return "true";
    }

    @PostMapping("/{id}/review")
    public String create(@PathVariable("id") Long id, ReviewVM request) {
        Task task = customTaskService.findOne(id);
        if (task == null || !userService.getUserWithAuthorities().isPresent()) return null;

        User u = userService.getUserWithAuthorities().get();
        Review review = new Review();
        review.setContent(request.getContent());
        review.setPoint(request.getPoint());
        if (task.getTasker().getId().equals(u.getId())) review.setUser(task.getMaster());
        else if (task.getMaster().getId().equals(u.getId())) review.setUser(task.getTasker());
        review.setTask(task);

        reviewRepository.save(review);
        return "true";
    }

    @PostMapping("/{id}/messages")
    public String message(@PathVariable("id") Long id, MessageVM request) {
        Task task = customTaskService.findOne(id);
        if (task == null || !userService.getUserWithAuthorities().isPresent()) return null;

        User u = userService.getUserWithAuthorities().get();
        UserInformation userInformation = userInformationRepository.getOne(u.getId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setUser(userInformation);
        message.setTask(task);

        messageRepository.save(message);
        return "true";
    }

    @GetMapping("/{id}/messages")
    public List<Message> getMessages(@PathVariable("id") Long id) {
        Task task = customTaskService.findOne(id);
        if (task == null || !userService.getUserWithAuthorities().isPresent()) return null;

        return new ArrayList<>(task.getMessages());
    }
}
