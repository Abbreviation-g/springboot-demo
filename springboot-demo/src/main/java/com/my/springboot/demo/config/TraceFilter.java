package com.my.springboot.demo.config;

import com.my.springboot.demo.utils.TraceIdUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TraceFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TraceFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        TraceIdUtil.generateTraceId();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Throwable t) {
            log.error("TraceFilter error", t);
        } finally {
            TraceIdUtil.removeTraceId();
        }
    }

    @Override
    public void destroy() {
        log.info("TraceFilter destroy");
    }
}
