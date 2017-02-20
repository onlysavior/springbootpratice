package com.taobao.yanye.springboot.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanye on 17-2-18.
 */
@ControllerAdvice
@EnableWebMvc
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req,
                                                     Throwable ex) {

        logger.error("occur error", ex);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(req.getPathInfo());
        errorResponse.setQuery(req.getQueryString());
        errorResponse.setMsg(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        logger.warn("The URL you have reached is not in service at this time (404)");
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("path",request.getContextPath());
        responseBody.put("message","The URL you have reached is not in service at this time (404).");
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    public static class ErrorResponse {
        private String path;
        private String query;
        private String msg;

        public ErrorResponse() {
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
