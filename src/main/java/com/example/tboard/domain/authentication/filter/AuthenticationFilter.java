package com.example.tboard.domain.authentication.filter;

import com.example.tboard.domain.member.Member;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("로그인 요청을 가로챕니다.");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        // POST 요청일 때만 로그인 처리
        String method = req.getMethod(); // GET, POST

        if(method.equals("POST")) {
            // 로그인 파라미터를 꺼내기
            String loginId = req.getParameter("loginId");
            String loginPw = req.getParameter("loginPw");
            List<Member> memberDB = new ArrayList<>();
            boolean isSucess = false;

            Member member = new Member("cha", "1234", "USER");
            Member member2 = new Member("hong", "1234", "ADMIN");
            memberDB.add(member);
            memberDB.add(member2);

            for (Member m : memberDB) {
                if (loginId.equals(m.getLoginId()) && loginPw.equals(m.getLoginPw())) {
                    isSucess = true;
                    session.setAttribute("loginedUser", m);
                    res.sendRedirect("/list");
                    return;
                }
            }

            if(!isSucess) {
                res.sendRedirect("/login?error");
                return;
            }
        }



        chain.doFilter(request, response);
    }
}
