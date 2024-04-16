package com.example.tboard.domain.article.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member create(String loginId, String loginPw, String name) {
        Member member = new Member();
        member.setName(name);
        member.setLoginId(loginId);
        member.setLoginPw(loginPw);

        return memberRepository.save(member);
    }


    public Member getMember(String loginId) {
        return memberRepository.findByLoginId(loginId).orElseThrow(
                () -> new RuntimeException("사용자를 찾을 수 없습니다.")
        );
    }
}
