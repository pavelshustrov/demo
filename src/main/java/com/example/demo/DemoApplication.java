package com.example.demo;

import com.example.demo.filters.HeadersFilter;
import com.example.demo.filters.LogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<LogFilter> loggingFilter(LogFilter logFilter) {
        FilterRegistrationBean<LogFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(logFilter);
        registrationBean.addUrlPatterns("/airport/*");

        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean<HeadersFilter> headerFilter(HeadersFilter headFilter) {
        FilterRegistrationBean<HeadersFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(headFilter);
        registrationBean.addUrlPatterns("/airport/*");

        return registrationBean;
    }
}
