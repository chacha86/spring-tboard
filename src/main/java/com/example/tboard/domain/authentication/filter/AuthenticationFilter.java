package com.example.tboard.domain.authentication.filter;

import com.example.tboard.domain.authentication.processor.DaoAuthenticationProcessor;
import com.example.tboard.domain.member.MemberAuth;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    DaoAuthenticationProcessor daoAuthenticationProcessor ;

    public AuthenticationFilter(DaoAuthenticationProcessor daoAuthenticationProcessor) {
        this.daoAuthenticationProcessor = daoAuthenticationProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("로그인 요청을 가로챕니다.");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // POST 요청일 때만 로그인 처리
        String method = req.getMethod(); // GET, POST

        if(method.equals("POST")) {
            String loginId = req.getParameter("loginId");
            String loginPw = req.getParameter("loginPw");
            // 로그인 파라미터를 꺼내기
            MemberAuth memberAuth = daoAuthenticationProcessor.authenticate(loginId, loginPw);

            session.setAttribute("loginedMember", memberAuth);

            res.sendRedirect("/list");
            return;
        }

        chain.doFilter(request, response);
    }
}
