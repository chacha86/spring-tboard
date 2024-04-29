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


    @GetMapping("/callback")
    public String callback(String code) {
        System.out.println("code : " + code);
        return "redirect:/list";
    }
    @GetMapping("/kakao-login")
    public String kakaoLogin() {

        StringBuilder sb = new StringBuilder();
        sb.append("https://kauth.kakao.com/oauth/authorize");
        sb.append("?client_id=62e93e2c976d6b7d96c64790d47f294e");
        sb.append("&redirect_uri=http://localhost:8088/callback");
        sb.append("&response_type=code");
        String uri = sb.toString();

        return "redirect:" + uri;
    }

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
