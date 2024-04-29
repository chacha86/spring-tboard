package com.example.tboard.domain.login;

import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Controller
public class LoginController {

    Repository articleRepository = new ArticleMySQLRepository();


    @GetMapping("/callback")
    public String callback(String code) {
        System.out.println("code : " + code);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.set("grant_type", "authorization_code");
        formData.set("client_id", "62e93e2c976d6b7d96c64790d47f294e");
        formData.set("redirect_uri", "http://localhost:8088/callback");
        formData.set("code", code);


        WebClient client = WebClient.create();

        Map<String, Object> result = client.post()
                .uri("https://kauth.kakao.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> userInfo = client.post().uri("https://kapi.kakao.com/v2/user/me")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Bearer " + result.get("access_token"))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        System.out.println(userInfo);

        return "redirect:/list";
    }

    @GetMapping("/kakao-login")
    public String kakaoLogin() {

        StringBuilder sb = new StringBuilder();
        sb.append("https://kauth.kakao.com/oauth/authorize");
        sb.append("?client_id=62e93e2c976d6b7d96c64790d47f294e");
        sb.append("&redirect_uri=http://localhost:8088/callback");
        sb.append("&response_type=code");
        String uri = sb.toString();

        return "redirect:" + uri;
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

//        Cookie cookie = new Cookie("username", "");
//        cookie.setMaxAge(0);
//
//        response.addCookie(cookie);

        session.invalidate();

        return "redirect:/list";

    }

    @GetMapping("/cookie-test")
    public String cookieTest(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 생성
        Cookie cookie1 = new Cookie("username", "chacha");
        Cookie cookie2 = new Cookie("age-test", "10");
        cookie2.setMaxAge(10); // 10초 뒤에 쿠키가 소멸하도록 만듦

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "cookie_test";
    }
}
