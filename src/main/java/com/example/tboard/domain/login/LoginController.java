package com.example.tboard.domain.login;

import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    Repository articleRepository = new ArticleMySQLRepository();


    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

//        Cookie cookie = new Cookie("username", "");
//        cookie.setMaxAge(0);
//
//        response.addCookie(cookie);

        session.invalidate();

        return "redirect:/list";

    }

    @GetMapping("/cookie-test")
    public String cookieTest(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 생성
        Cookie cookie1 = new Cookie("username", "chacha");
        Cookie cookie2 = new Cookie("age-test", "10");
        cookie2.setMaxAge(10); // 10초 뒤에 쿠키가 소멸하도록 만듦

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "cookie_test";
    }
}
