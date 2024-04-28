package com.example.tboard.domain.authentication.processor;

import com.example.tboard.domain.member.MemberAuth;
import com.example.tboard.domain.member.Oauth2MemberAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationProcessor {

    private final OauthRegistrationBean oauthRegistrationBean;

    public MemberAuth authenticate(String code) {

        WebClient webClient = WebClient.create();
        System.out.println("oauth2 처리 요청");

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", oauthRegistrationBean.getGrantType());
        formData.add("client_id", oauthRegistrationBean.getClientId());
        formData.add("redirect_uri", oauthRegistrationBean.getRedirectUri());
        formData.add("code", code);
        Map<String, Object> result = new HashMap<>();
        try {

            result = webClient.post()
                    .uri(oauthRegistrationBean.getTokenUri())
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

            userInfo = webClient.get().uri(oauthRegistrationBean.getUserInfoUri())
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
