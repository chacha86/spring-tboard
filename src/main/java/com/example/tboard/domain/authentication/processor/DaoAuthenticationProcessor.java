package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.Member;
import com.example.tboard.domain.member.MemberAuth;
import com.example.tboard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaoAuthenticationProcessor {

    private final MemberRepository memberRepository;
    public MemberAuth authenticate(String loginId, String loginPw) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow();

        if(loginPw.equals(member.getLoginPw())) {
            return new MemberAuth(member.getLoginId(), member.getLoginPw(), "USER");
        }

        throw new RuntimeException("자격 증명 실패");

    }
}
