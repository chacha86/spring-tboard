package com.example.tboard.domain.article.member.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
public class FailureHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hihi");
    }
}
