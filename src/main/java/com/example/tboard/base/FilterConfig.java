package com.example.tboard.base;

import com.example.tboard.domain.article.member.authentication.AuthenticationProcessor;
import com.example.tboard.domain.article.member.authentication.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private AuthenticationProcessor authenticationProcessor;

    @Bean
    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilter() {
        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginCheckFilter(authenticationProcessor));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
