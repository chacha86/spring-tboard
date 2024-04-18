package com.example.tboard.domain.article.member.authentication.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private String loginId;
    private String loginPw;
}
