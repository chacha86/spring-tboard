package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.Member;

import java.util.ArrayList;
import java.util.List;

public class MemoryAuthenticationProcessor {

    public Member authenticate(String loginId, String loginPw) {

        List<Member> memberDB = new ArrayList<>();

        Member member = new Member("cha", "1234", "USER");
        Member member2 = new Member("hong", "1234", "ADMIN");
        memberDB.add(member);
        memberDB.add(member2);

        for (Member m : memberDB) {
            if (loginId.equals(m.getLoginId()) && loginPw.equals(m.getLoginPw())) {
                return m;
            }
        }

        return null;
    }
}
