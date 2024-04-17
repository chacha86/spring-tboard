package com.example.tboard.domain.login;

import com.example.tboard.domain.article.Article;
import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(String loginId, String loginPw, HttpServletRequest request, Model model) {
        String dbUser = "cha";
        String dbPass = "1234";
        String role = "USER";

        if(loginId.equals(dbUser) && loginPw.equals(dbPass)) {

            List<Article> articleList = articleRepository.findAll();
//            request.setAttribute("articleList", articleList);
            model.addAttribute("articleList", articleList);
            model.addAttribute("loginedUser", loginId);

//            request.getRequestURI()
            return "redirect:/list"; // redirection
//            return "list"; // forwarding
        }

        return "redirect:/login?error";
    }
}
