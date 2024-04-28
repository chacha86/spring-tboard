package com.example.tboard.domain.login;

import com.example.tboard.domain.article.ArticleMySQLRepository;
import com.example.tboard.domain.article.Repository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    Repository articleRepository = new ArticleMySQLRepository();

//    @GetMapping("/kakao_callback")
//    @ResponseBody
//    public String callback(String code) {
//        System.out.println(code);
//        WebClient webClient = WebClient.create();
////        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
////        requestBody.add("grant_type", "authorization_code");
////        requestBody.add("client_id", "ebf4c71717c85af7ffb54016b89ab632");
////        requestBody.add("redirect_uri", "http://localhost:8080/kakao_callback");
////        requestBody.add("code", code);
//        String param = "grant_type=authorization_code&client_id=ebf4c71717c85af7ffb54016b89ab632&redirect_uri=http://localhost:8080/kakao_callback&code=" + code;
//
////        Map<String, Object> result = webClient.post().uri("https://kauth.kakao.com/oauth/token")
////                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
////                .bodyValue(param)
////                .retrieve().bodyToMono(Map.class).block();
//
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add("grant_type", "authorization_code");
//        formData.add("client_id", "ebf4c71717c85af7ffb54016b89ab632");
//        formData.add("redirect_uri", "http://localhost:8088/kakao_callback");
//        formData.add("code", code);
//
//        Map<String, Object> result = webClient.post()
//                .uri("https://kauth.kakao.com/oauth/token")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .bodyValue(formData)
//                .retrieve()
//                .bodyToMono(Map.class).block();
//
//        Map<String, Object> userInfo = webClient.get().uri("https://kapi.kakao.com/v2/user/me")
//                .header("Authorization", "Bearer " + result.get("access_token"))
//                .retrieve()
//                .bodyToMono(Map.class).block();
//        System.out.println(userInfo);
//        Map<String, Object> userProps = (Map<String, Object>)userInfo.get("properties");
//        System.out.println(userInfo.get("id"));
//        System.out.println(userProps.get("nickname"));
//        System.out.println(userProps.get("profile_image"));
//
//        return "callback";
//    }

    @GetMapping("wc-test")
    @ResponseBody
    public String webClientTest() {
        return """
                {
                    "name": "chacha",
                    "age": 10,
                    "address": "seoul"
                }
                """;
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
