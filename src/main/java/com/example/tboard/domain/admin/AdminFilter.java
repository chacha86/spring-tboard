package com.example.tboard.domain.admin;

import com.example.tboard.domain.member.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("모든 요청은 저를 지나갑니다.");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = req.getSession();
        Member member = (Member)session.getAttribute("loginedUser");

        if(member == null) {
            throw new RuntimeException("로그인 후 이용해주세요.");
        }

        if(!member.getRole().equals("ADMIN")) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
