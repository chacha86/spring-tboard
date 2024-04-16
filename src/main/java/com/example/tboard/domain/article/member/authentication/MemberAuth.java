package com.example.tboard.domain.article.member.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAuth {
    private String loginId;
    private String authToken;
    private String role;

    public MemberAuth(String loginId, String role) {
        this.loginId = loginId;
        this.role = role;
    }
}
