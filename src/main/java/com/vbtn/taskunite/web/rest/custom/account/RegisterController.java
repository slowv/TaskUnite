package com.vbtn.taskunite.web.rest.custom.account;

import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.service.MailService;
import com.vbtn.taskunite.service.custom.account.RegisterService;
import com.vbtn.taskunite.web.rest.custom.vm.RegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private MailService mailService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("register", new RegisterVM());
        return "account/register";
    }

    @PostMapping
    public String registerAccount(@Valid RegisterVM managedUserVM) {
        User user = registerService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);

        return "redirect:/login";
    }
}
