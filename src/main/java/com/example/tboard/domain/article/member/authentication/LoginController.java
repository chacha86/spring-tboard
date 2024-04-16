package com.example.tboard.domain.article.member.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final FailureHandler failureHandler;

    @PostMapping("/login")
    public String login(String loginId, String loginPw, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        try {
            MemberAuth memberAuth = loginService.loginProcess(loginId, loginPw);
            session.setAttribute("memberAuth", memberAuth);
        } catch (Exception e) {
            failureHandler.handle(request, response);
            return "redirect:/login?error";
        }

        return "redirect:/list";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/list";
    }

    public FailureHandler getFailureHandler() {
        return failureHandler;
    }
}
