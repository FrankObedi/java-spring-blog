package org.frankobedi.SpringBlog.controller;

import org.frankobedi.SpringBlog.models.Account;
import org.frankobedi.SpringBlog.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")    
    public String register(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registeruser(@ModelAttribute Account account) {
        accountService.save(account);        
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {        
        return "login";
    }
    
    @GetMapping("/profile")
    public String profile(Model model){
        return "profile";
    }
}
