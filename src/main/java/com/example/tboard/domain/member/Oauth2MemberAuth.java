package com.example.tboard.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Oauth2MemberAuth extends MemberAuth {

    private String accessToken;
    private String refreshToken;
    private String tokenType;

}

