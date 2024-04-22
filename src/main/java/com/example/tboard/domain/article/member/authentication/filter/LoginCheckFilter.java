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
    private final String SUCCESS_URL = "/list";
    private final String ERROR_URL = "/login?error";
    private final String PROCESS_URL = "/login";
    private final String PROCESS_METHOD = "POST";
    private final String ID_NAME = "loginId";
    private final String PW_NAME = "loginPw";
    private final String AUTH_NAME = "memberAuth";

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

        if (requestURI.equals(PROCESS_URL) && method.equals(PROCESS_METHOD)) {
            String loginId = req.getParameter(ID_NAME);
            String loginPw = req.getParameter(PW_NAME);

            try {
                MemberAuth memberAuth = authenticationProcessor.authenticate(makeAuthToken(loginId, loginPw));
                req.getSession().setAttribute(AUTH_NAME, memberAuth);
                res.sendRedirect(SUCCESS_URL);

            } catch (Exception e) {
                res.sendRedirect(ERROR_URL);
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
