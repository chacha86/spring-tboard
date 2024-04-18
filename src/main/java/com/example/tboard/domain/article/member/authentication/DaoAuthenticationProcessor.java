package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.MemberService;
import com.example.tboard.domain.article.member.authentication.filter.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaoAuthenticationProcessor implements AuthenticationProcessor {

    private final MemberAuthService memberAuthService;
    @Override
    public MemberAuth authenticate(AuthToken authToken) {

        MemberAuth memberAuth = memberAuthService.loadUserByUsername(authToken.getLoginId());
        String pw = authToken.getLoginPw();
        if (memberAuth.getAuthToken().equals(pw)) {
            memberAuth.setAuthToken(pw);
            return memberAuth;
        }

        throw new RuntimeException("인증 실패");

    }


}
