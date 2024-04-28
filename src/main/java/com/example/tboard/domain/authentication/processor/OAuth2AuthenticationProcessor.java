package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.MemberAuth;
import com.example.tboard.domain.member.Oauth2MemberAuth;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuth2AuthenticationProcessor {

    private final String OAUTH2_TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    private final String OAUTH2_GRANT_TYPE = "authorization_code";

    private final String OAUTH2_CLIENT_ID = "ebf4c71717c85af7ffb54016b89ab632";
    private final String OAUTH2_REDIRECT_URI = "http://localhost:8088/oauth/callback";
    private final String OATUH2_USERINFO_URL = "https://kapi.kakao.com/v2/user/me";

    public MemberAuth authenticate(String code) {

        WebClient webClient = WebClient.create();
        System.out.println("oauth2 처리 요청");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", OAUTH2_GRANT_TYPE);
        formData.add("client_id", OAUTH2_CLIENT_ID);
        formData.add("redirect_uri", OAUTH2_REDIRECT_URI);
        formData.add("code", code);
        Map<String, Object> result = new HashMap<>();
        try {

            result = webClient.post()
                    .uri(OAUTH2_TOKEN_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(Map.class).block();

        } catch (Exception e) {
            throw new RuntimeException("oauth2 인증 실패");
        }

        String accessToken = (String) result.get("access_token");
        String refreshToken = (String) result.get("refresh_token");
        String tokenType = (String) result.get("token_type");

        Map<String, Object> userInfo = new HashMap<>();
        try {

            userInfo = webClient.get().uri(OATUH2_USERINFO_URL)
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보 요청 실패");
        }

        Oauth2MemberAuth oauth2MemberAuth = new Oauth2MemberAuth(accessToken, refreshToken, tokenType);
        oauth2MemberAuth.setLoginId(String.valueOf(userInfo.get("id")));
        oauth2MemberAuth.setLoginPw("");
        oauth2MemberAuth.setRole("USER");

        return oauth2MemberAuth;
    }
}
