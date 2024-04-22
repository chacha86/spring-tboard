package com.example.tboard.domain.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/admin-page")
    public String adminPage(HttpSession session) {


        return "admin_page";
    }

    @GetMapping("/stat")
    @ResponseBody
    public String stat(HttpSession session) {

        return "통계 페이지입니다.";
    }

    @GetMapping("/user-management")
    @ResponseBody
    public String userManagement() {

        return "유저관리 페이지입니다.";
    }
}
