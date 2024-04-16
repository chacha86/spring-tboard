package com.example.tboard.domain.article.member;

import com.example.tboard.domain.article.member.authentication.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "member/login_form";
    }

}
