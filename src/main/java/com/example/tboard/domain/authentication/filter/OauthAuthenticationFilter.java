package com.example.tboard.domain.authentication.filter;

import com.example.tboard.domain.authentication.processor.OAuth2AuthenticationProcessor;
import com.example.tboard.domain.member.MemberAuth;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class OauthAuthenticationFilter implements Filter {
    private final String OAUTH2_AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";
    private final String OAUTH2_RESPONSE_TYPE = "code";

    private final String OAUTH2_CLIENT_ID = "ebf4c71717c85af7ffb54016b89ab632";
    private final String OAUTH2_REDIRECT_URI = "http://localhost:8088/oauth/callback";
    private OAuth2AuthenticationProcessor authenticationProcessor;

    public OauthAuthenticationFilter(OAuth2AuthenticationProcessor oAuth2AuthenticationProcessor) {
        this.authenticationProcessor = oAuth2AuthenticationProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("oauth2 요청을 가로챕니다.");

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        WebClient webClient = WebClient.create();
        String uri = req.getRequestURI();

        if (uri.equals("/oauth/login")) {

            System.out.println("oauth2 로그인 요청");
            StringBuilder uriBuilder = new StringBuilder();
            uriBuilder.append(OAUTH2_AUTHORIZATION_URL)
                    .append("?client_id=").append(OAUTH2_CLIENT_ID)
                    .append("&redirect_uri=").append(OAUTH2_REDIRECT_URI)
                    .append("&response_type=").append(OAUTH2_RESPONSE_TYPE);

            res.sendRedirect(uriBuilder.toString());
            return;
        }

        if (uri.equals("/oauth/callback")) {

            String code = req.getParameter("code");
            MemberAuth memberAuth = authenticationProcessor.authenticate(code);
            req.getSession().setAttribute("loginedMember", memberAuth);
            res.sendRedirect("/list");
            return;
        }

        chain.doFilter(request, response);
    }
}
