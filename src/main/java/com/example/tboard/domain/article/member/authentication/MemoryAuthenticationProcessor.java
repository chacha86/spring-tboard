package com.example.tboard.domain.article.member.authentication;

import org.springframework.stereotype.Component;

@Component
public class MemoryAuthenticationProcessor implements AuthenticationProcessor {
    public MemberAuth authenticate(String loginId, String loginPw) {
        String dbUser = "chacha";
        String dbPw = "1234";

        if (dbUser.equals(loginId) && dbPw.equals(loginPw)) {
            MemberAuth memberAuth = new MemberAuth(loginId, "USER");
            memberAuth.setAuthToken(loginPw);
            return memberAuth;
        }

        throw new RuntimeException("인증 실패");
    }
}
