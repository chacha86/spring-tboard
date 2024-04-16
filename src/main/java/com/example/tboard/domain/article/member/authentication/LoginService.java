package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final DaoAuthenticationProcessor authenticationProcessor;

    public MemberAuth loginProcess(String loginId, String loginPw) {
        return authenticationProcessor.authenticate(loginId, loginPw);
    }
}
