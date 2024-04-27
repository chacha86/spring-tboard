package com.example.tboard.domain.authentication.filter;

import com.example.tboard.domain.authentication.processor.MemoryAuthenticationProcessor;
import com.example.tboard.domain.member.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    MemoryAuthenticationProcessor memoryAuthenticationProcessor = new MemoryAuthenticationProcessor();
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
            Member member = memoryAuthenticationProcessor.authenticate(loginId, loginPw);

            session.setAttribute("loginedMember", member);
        }

        chain.doFilter(request, response);
    }
}
