package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfig {

    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public AuthenticationProcessor authenticationProcessor() {
        return new DaoAuthenticationProcessor(new MyMemberAuthService(memberRepository));
    }
}
