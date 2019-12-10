package com.vbtn.taskunite.web.rest.custom;

import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.repository.AuthorityRepository;
import com.vbtn.taskunite.service.PaymentInformationService;
import com.vbtn.taskunite.service.StatisticService;
import com.vbtn.taskunite.service.UserInformationService;
import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.task.CustomTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    UserService userService;
    @Autowired
    UserInformationService userInformationService;
    @Autowired
    PaymentInformationService paymentInformationService;
    @Autowired
    StatisticService statisticService;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    CustomTaskService taskService;

    @GetMapping
    public String dashboard(Model model) {
        final Optional<User> user = userService.getUserWithAuthorities();
        if (user.isPresent()) {
            model.addAttribute("tasks", taskService.findAllWithStatus1(user.get().getId()));
            model.addAttribute("authorities", user.get().getAuthorities());
            model.addAttribute("user", user.get());
            model.addAttribute("userInformation", userInformationService.findOne(user.get().getId()).get());
            model.addAttribute("payment", paymentInformationService.findOne(user.get().getId()).get());
            model.addAttribute("statistic", statisticService.findOne(user.get().getId()).get());
            return "dashboard/dashboard";
        }
        return "redirect:/";
    }
}
