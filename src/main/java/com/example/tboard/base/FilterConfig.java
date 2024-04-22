package com.example.tboard.base;

import com.example.tboard.domain.admin.AdminFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AdminFilter> registrationBean() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        AdminFilter adminFilter = new AdminFilter();

        registrationBean.setFilter(adminFilter);
        registrationBean.addUrlPatterns("/**");  // 필터가 적용될 URL 패턴

        return registrationBean;
    }
}
