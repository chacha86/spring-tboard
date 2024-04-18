package com.example.tboard.domain.login;

import com.example.tboard.domain.article.Article;
import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    Repository articleRepository = new ArticleMySQLRepository();

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @PostMapping("/login")
    public String login(String loginId, String loginPw, HttpServletResponse response, Model model) {
        String dbUser = "cha";
        String dbPass = "1234";
        String role = "USER";

        if (loginId.equals(dbUser) && loginPw.equals(dbPass)) {

            List<Article> articleList = articleRepository.findAll();
            model.addAttribute("articleList", articleList);
            model.addAttribute("loginedUser", loginId);

            // 로그인 성공하면 해당 유저를 쿠키로 저장. 이제 부터 해당 쿠키를 제시하면 로그인 인증된 유저로 활동 가능
            Cookie cookie1 = new Cookie("username", loginId);
            response.addCookie(cookie1);

            return "list"; // forwarding
        }

        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

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
