package com.vbtn.taskunite.web.rest.custom.api;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.ReviewRepository;
import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.task.CustomTaskService;
import com.vbtn.taskunite.web.rest.custom.vm.ReviewVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/room")
public class RoomAPI {
    @Autowired
    CustomTaskService customTaskService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserService userService;

    @PostMapping("/{id}/complete")
    public String complete(@PathVariable("id") Long id, @RequestParam("duration") Double duration) {
        Task task = customTaskService.findOne(id);
        if (task == null) return null;
        Duration d = Duration.of((long) (duration * 60), ChronoUnit.MINUTES);
        task.setDuration(d);
        task.setTo(task.getFrom().plus(d));
        task.setTotalPrice(task.getPrice() * duration);
        task.setStatus(2);

        customTaskService.save(task);

        UserInformation tasker = task.getTasker();
        UserInformation master = task.getMaster();
        PaymentInformation paymentTasker = tasker.getPayment();
        PaymentInformation paymentMaster = master.getPayment();
        Statistic statisticTasker = tasker.getStatistic();
        Statistic statisticMaster = master.getStatistic();

        paymentMaster.setBalance(paymentMaster.getBalance() - (11-statisticMaster.getLevel())/100*task.getTotalPrice());
        paymentTasker.setBalance(paymentTasker.getBalance() + (89+statisticTasker.getLevel())/100*task.getTotalPrice());
        statisticMaster.setExperience(statisticMaster.getExperience() + 20);
        statisticMaster.setExperience(statisticMaster.getCompletedTask() + 1);
        statisticTasker.setExperience(statisticTasker.getExperience() + 20);
        statisticTasker.setExperience(statisticTasker.getCompletedTask() + 1);

        customTaskService.saveAll(task, paymentTasker, statisticTasker, paymentMaster, statisticMaster);
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
}
