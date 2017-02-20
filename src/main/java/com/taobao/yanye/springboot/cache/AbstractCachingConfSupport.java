package com.taobao.yanye.springboot.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Collections;


/**
 * Created by yanye on 17-2-19.
 */
public abstract class AbstractCachingConfSupport extends CachingConfigurerSupport {
    @Bean
    public KeyGenerator keyGenerator(){
        return (target,
                method,
                params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                logException(exception, key);
                throw exception;
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                logException(exception, key);
                throw exception;
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                logException(exception, key);
                throw exception;
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                logException(exception, null);
                throw exception;
            }

            private void logException(RuntimeException e,
                                      Object key) {
                logger.error("cache exception, key: " + key, e);
            }
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return new AbstractCacheResolver(cacheManager()) {
            @Override
            protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
                String key = (String)keyGenerator().generate(context.getTarget(),
                        context.getMethod(),
                        context.getArgs());


                return Collections.singletonList(key);
            }
        };
    }

    private static final Logger logger = LoggerFactory.getLogger(AbstractCachingConfSupport.class);
}
