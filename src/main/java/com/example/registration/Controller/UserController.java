package com.example.registration.Controller;

import com.example.registration.model.User;
import com.example.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        System.out.println("registerRequest:" + user);
        User registerUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail());
        return registerUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        System.out.println("loginRequest" + user);
        User authetication = userService.authetication(user.getLogin(), user.getPassword());
        if (authetication != null) {
            model.addAttribute("userLogin", authetication.getLogin());
            return "personal_page";
        } else {
            return "error_page";
        }
    }

}
