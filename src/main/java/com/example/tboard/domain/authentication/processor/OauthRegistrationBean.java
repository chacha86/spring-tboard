package com.example.tboard.domain.authentication.processor;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OauthRegistrationBean {

    @Value("${social.authorization-uri}")
    private String authorizationUri;
    @Value("${social.client-id}")
    private String clientId;
    @Value("${social.redirect-uri}")
    private String redirectUri;
    @Value("${social.token-uri}")
    private String tokenUri;
    @Value("${social.grant-type}")
    private String grantType;
    @Value("${social.user-info-uri}")
    private String userInfoUri;
}
