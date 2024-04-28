package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.Member;
import com.example.tboard.domain.member.FormMemberAuth;
import com.example.tboard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaoAuthenticationProcessor {

    private final MemberRepository memberRepository;
    public FormMemberAuth authenticate(String loginId, String loginPw) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow();

        if(loginPw.equals(member.getLoginPw())) {
            return new FormMemberAuth(member.getLoginId(), member.getLoginPw(), "USER");
        }

        throw new RuntimeException("자격 증명 실패");

    }
}
