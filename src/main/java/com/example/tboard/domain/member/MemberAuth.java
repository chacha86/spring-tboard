package com.example.tboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class MemberAuth {
    private String loginId;
    private String loginPw;
    private String role;
}

