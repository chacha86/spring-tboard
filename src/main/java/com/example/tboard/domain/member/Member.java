package com.example.tboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Member {
    private String loginId;
    private String loginPw;
    private String role;
}
