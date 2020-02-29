package com.example.demo.filters;

import com.example.demo.exceptions.IllegalHeaderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
@Slf4j
public class HeadersFilter implements Filter {
    private final List<String> clientHeaders = Collections.unmodifiableList(Arrays.asList("client.ID", "client.ThreadName", "client.SendingTime"));
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        boolean allPresent = clientHeaders.stream().allMatch(name -> req.getHeader(name) != null);
        if (!allPresent) {
            throw new IllegalHeaderException(clientHeaders.toString() + " should be presented");
        }

        Map<String, String> copyHeaders = clientHeaders.stream().
                collect(toMap(Function.identity(), req::getHeader));


        HttpServletResponse res = (HttpServletResponse) response;
        copyHeaders.forEach(res::addHeader);

        res.addHeader("server.ID", UUID.randomUUID().toString());
        res.addHeader("server.ThreadName", Thread.currentThread().getName());
        res.addHeader("server.SendingTime", LocalDateTime.now().toString());

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init() done");
    }

    @Override
    public void destroy() {
    }
}
