package com.example.tboard.domain.article.member.authentication.filter;

import com.example.tboard.domain.article.member.authentication.AuthenticationProcessor;
import com.example.tboard.domain.article.member.authentication.MemberAuth;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    private final AuthenticationProcessor authenticationProcessor;

    public LoginCheckFilter(AuthenticationProcessor authenticationProcessor) {
        this.authenticationProcessor = authenticationProcessor;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String method = req.getMethod();
        String requestURI = req.getRequestURI();
        System.out.println("hihi");

        if (requestURI.equals("/login") && method.equals("POST")) {
            System.out.println("login process");
            String loginId = req.getParameter("loginId");
            String loginPw = req.getParameter("loginPw");

            try {
                MemberAuth memberAuth = authenticationProcessor.authenticate(makeAuthToken(loginId, loginPw));
                req.getSession().setAttribute("memberAuth", memberAuth);
                res.sendRedirect("/list");
            } catch (Exception e) {
                res.sendRedirect("/login?error");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private AuthToken makeAuthToken(String loginId, String loginPw) {
        return new AuthToken(loginId, loginPw);
    }
}
