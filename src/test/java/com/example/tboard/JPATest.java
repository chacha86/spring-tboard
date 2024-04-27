package com.example.tboard;

import com.example.tboard.domain.member.Member;
import com.example.tboard.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPATest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void t1() {
        Member member = new Member();
        member.setLoginId("cha");
        member.setLoginPw("1234");
        member.setNickname("cha");

        memberRepository.save(member);
    }
}
