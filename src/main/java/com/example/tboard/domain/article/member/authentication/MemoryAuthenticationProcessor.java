package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.authentication.filter.AuthToken;
import org.springframework.stereotype.Component;

@Component
public class MemoryAuthenticationProcessor implements AuthenticationProcessor {
    public MemberAuth authenticate(AuthToken authToken) {
        String dbUser = "chacha";
        String dbPw = "1234";

        String loginId = authToken.getLoginId();
        String loginPw = authToken.getLoginPw();

        if (dbUser.equals(loginId) && dbPw.equals(loginPw)) {
            MemberAuth memberAuth = new MemberAuth(loginId, "USER");
            memberAuth.setAuthToken(loginPw);
            return memberAuth;
        }

        throw new RuntimeException("인증 실패");
    }
}
