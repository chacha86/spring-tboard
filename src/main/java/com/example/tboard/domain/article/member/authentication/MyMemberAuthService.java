package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.Member;
import com.example.tboard.domain.article.member.MemberRepository;
import com.example.tboard.domain.article.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyMemberAuthService implements MemberAuthService {

    private final MemberRepository memberRepository;

    @Override
    public MemberAuth loadUserByUsername(String loginId) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new RuntimeException("사용자를 찾을 수 없습니다.")
        );

        MemberAuth memberAuth = new MemberAuth(member.getLoginId(), "USER");
        memberAuth.setAuthToken(member.getLoginPw());

        return memberAuth;
    }
}
