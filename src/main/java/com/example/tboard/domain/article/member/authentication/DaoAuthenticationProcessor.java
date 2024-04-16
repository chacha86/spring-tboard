package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaoAuthenticationProcessor implements AuthenticationProcessor {

    private final MemberAuthService memberAuthService;
    @Override
    public MemberAuth authenticate(String loginId, String loginPw) {

        MemberAuth memberAuth = memberAuthService.loadUserByUsername(loginId);

        if (memberAuth.getAuthToken().equals(loginPw)) {
            memberAuth.setAuthToken(loginPw);
            return memberAuth;
        }

        throw new RuntimeException("인증 실패");

    }


}
