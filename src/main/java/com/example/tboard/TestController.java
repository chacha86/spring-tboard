package com.example.tboard;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return """
                {
                     "name": "chacha",
                     "age" : 30,
                     "home" : "soeul"
                }
                            """;
    }

    @GetMapping("/api")
    @ResponseBody
    public Map apiCall() {
        // http 통신을 이용해서 hello 메서드를 호출할 수 있냐?
        // http 통신 라이브러리 사용
        // RestTemplate, WebClient
        WebClient client = WebClient.create();

        Map<String, Object> result = client.get()
                .uri("http://localhost:8088/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        System.out.println(result.get("name"));
        System.out.println(result.get("age"));
        System.out.println(result.get("home"));

        return result;
    }
}
