package com.example.tboard.domain.authentication.filter;

import com.example.tboard.domain.authentication.processor.OAuth2AuthenticationProcessor;
import com.example.tboard.domain.authentication.processor.OauthRegistrationBean;
import com.example.tboard.domain.member.MemberAuth;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class OauthAuthenticationFilter implements Filter {
    private final OauthRegistrationBean oauthRegistrationBean;
    private OAuth2AuthenticationProcessor authenticationProcessor;

    public OauthAuthenticationFilter(OAuth2AuthenticationProcessor oAuth2AuthenticationProcessor,
                                     OauthRegistrationBean oauthRegistrationBean) {
        this.authenticationProcessor = oAuth2AuthenticationProcessor;
        this.oauthRegistrationBean = oauthRegistrationBean;
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
            uriBuilder.append(oauthRegistrationBean.getAuthorizationUri())
                    .append("?client_id=").append(oauthRegistrationBean.getClientId())
                    .append("&redirect_uri=").append(oauthRegistrationBean.getRedirectUri())
                    .append("&response_type=").append("code");

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
