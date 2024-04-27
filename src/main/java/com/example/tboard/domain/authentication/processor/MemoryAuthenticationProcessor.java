package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.MemberAuth;

import java.util.ArrayList;
import java.util.List;

public class MemoryAuthenticationProcessor {

    public MemberAuth authenticate(String loginId, String loginPw) {

        List<MemberAuth> memberAuthDB = new ArrayList<>();

        MemberAuth memberAuth = new MemberAuth("cha", "1234", "USER");
        MemberAuth memberAuth2 = new MemberAuth("hong", "1234", "ADMIN");
        memberAuthDB.add(memberAuth);
        memberAuthDB.add(memberAuth2);

        for (MemberAuth m : memberAuthDB) {
            if (loginId.equals(m.getLoginId()) && loginPw.equals(m.getLoginPw())) {
                return m;
            }
        }

        return null;
    }
}
