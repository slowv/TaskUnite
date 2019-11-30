package com.vbtn.taskunite.web.rest.client.account;

import com.vbtn.taskunite.domain.User;
import com.vbtn.taskunite.service.MailService;
import com.vbtn.taskunite.service.RegisterService;
import com.vbtn.taskunite.web.rest.client.vm.RegisterVM;
import com.vbtn.taskunite.web.rest.errors.InvalidPasswordException;
import com.vbtn.taskunite.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;

    private final MailService mailService;

    public RegisterController(RegisterService registerService, MailService mailService) {
        this.registerService = registerService;
        this.mailService = mailService;
    }

    @GetMapping
    public String register(Model model) {
        RegisterVM vm = new RegisterVM();
        model.addAttribute("register", vm);
        return "account/register";
    }

    @PostMapping
    public String registerAccount(@Valid RegisterVM managedUserVM) { // TODO: properly handle utf8
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = registerService.registerUser(managedUserVM, managedUserVM.getPassword());
        mailService.sendActivationEmail(user);
        return "redirect:/";
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
