package com.example.tboard.domain.login;

import com.example.tboard.domain.article.Article;
import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import com.example.tboard.domain.member.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    Repository articleRepository = new ArticleMySQLRepository();


    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @PostMapping("/login")
    public String login(String loginId, String loginPw, HttpServletResponse response, Model model, HttpSession session) {

        List<Member> memberDB = new ArrayList<>();

        Member member = new Member("cha", "1234", "USER");
        Member member2 = new Member("hong", "1234", "ADMIN");
        memberDB.add(member);
        memberDB.add(member2);

        for (Member m : memberDB) {
            if (loginId.equals(m.getLoginId()) && loginPw.equals(m.getLoginPw())) {

                List<Article> articleList = articleRepository.findAll();
                model.addAttribute("articleList", articleList);
                session.setAttribute("loginedUser", m);

                return "list"; // forwarding
            }
        }

        return "redirect:/login?error";
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
