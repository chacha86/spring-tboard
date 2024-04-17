package com.example.tboard.domain.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @PostMapping("/login")
    public String login(String loginId, String loginPw) {
        String dbUser = "cha";
        String dbPass = "1234";
        String role = "USER";

        if(loginId.equals(dbUser) && loginPw.equals(dbPass)) {
            return "redirect:/list";
        }

        return "redirect:/login?error";
    }
}
