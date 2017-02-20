package com.taobao.yanye.springboot.web.filter;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yanye on 17-2-19.
 */
public class TimeTracerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        DateTime start = DateTime.now();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            DateTime end = DateTime.now();

            Duration dur = new Duration(start, end);
            logger.info("use time cost; " + dur.getMillis());
        }
    }

    @Override
    public void destroy() {

    }

    private static final Logger logger = LoggerFactory.getLogger(TimeTracerFilter.class);
}
