package com.example.tboard.base;

import com.example.tboard.domain.admin.AdminFilter;
import com.example.tboard.domain.authentication.filter.AuthenticationFilter;
import com.example.tboard.domain.authentication.processor.DaoAuthenticationProcessor;
import com.example.tboard.domain.login.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final DaoAuthenticationProcessor daoAuthenticationProcessor;

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        AdminFilter adminFilter = new AdminFilter();

        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/admin/*");  // 필터가 적용될 URL 패턴
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        LoginFilter loginFilter = new LoginFilter();

        registrationBean.setFilter(loginFilter);
        registrationBean.addUrlPatterns("/admin/*");  // 필터가 적용될 URL 패턴
        registrationBean.addUrlPatterns("/add");
        registrationBean.addUrlPatterns("/detail/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(daoAuthenticationProcessor);

        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/login");

        return registrationBean;
    }
}
