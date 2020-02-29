package com.example.demo.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

import static java.util.stream.Collectors.joining;

@Component
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String headersToString = Collections.list(httpServletRequest.getHeaderNames()).stream().map(s -> s + ": " + httpServletRequest.getHeader(s)).
                collect(joining(", ", "headers {", "}"));
        String servletPath = httpServletRequest.getServletPath();
        String method = httpServletRequest.getMethod();
        log.info("Logging Request:");
        log.info("httpServletRequest { {}, method: {} , servletPath : {} }", headersToString, method, servletPath);

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init() done");
    }

    @Override
    public void destroy() {}
}
