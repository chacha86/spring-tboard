package com.example.tboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormMemberAuth extends MemberAuth {

    public FormMemberAuth(String cha, String number, String user) {
        super(cha, number, user);
    }
}
