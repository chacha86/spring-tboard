package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.FormMemberAuth;

import java.util.ArrayList;
import java.util.List;

public class MemoryAuthenticationProcessor {

    public FormMemberAuth authenticate(String loginId, String loginPw) {

        List<FormMemberAuth> formMemberAuthDB = new ArrayList<>();

        FormMemberAuth formMemberAuth = new FormMemberAuth("cha", "1234", "USER");
        FormMemberAuth formMemberAuth2 = new FormMemberAuth("hong", "1234", "ADMIN");
        formMemberAuthDB.add(formMemberAuth);
        formMemberAuthDB.add(formMemberAuth2);

        for (FormMemberAuth m : formMemberAuthDB) {
            if (loginId.equals(m.getLoginId()) && loginPw.equals(m.getLoginPw())) {
                return m;
            }
        }

        return null;
    }
}
