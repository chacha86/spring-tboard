package com.example.tboard.domain.admin;

import com.example.tboard.domain.member.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @GetMapping("/admin-page")
    public String adminPage(HttpSession session) {
        Member member = (Member)session.getAttribute("loginedUser");

        if(member == null) {
            throw new RuntimeException("로그인 후 이용해주세요.");
        }

        if(!member.getRole().equals("ADMIN")) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }

        return "admin_page";
    }

    @GetMapping("/stat")
    @ResponseBody
    public String stat(HttpSession session) {
        Member member = (Member)session.getAttribute("loginedUser");

        if(member == null) {
            throw new RuntimeException("로그인 후 이용해주세요.");
        }

        if(!member.getRole().equals("ADMIN")) {
            throw new RuntimeException("관리자만 접근 가능합니다.");
        }

        return "통계 페이지입니다.";
    }

    @GetMapping("/user-management")
    @ResponseBody
    public String userManagement() {

        return "유저관리 페이지입니다.";
    }
}
