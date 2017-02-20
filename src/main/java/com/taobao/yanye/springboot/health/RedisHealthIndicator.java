package com.taobao.yanye.springboot.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yanye on 17-2-19.
 */
@Component
public class RedisHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            RedisConnection rc = factory.getConnection();

            if (null == rc) {
                builder.down();
            } else {
                builder.up();
            }
        } catch (Exception e) {
            logger.error("", e);
            builder.down(e);
        }
    }

    @Autowired
    JedisConnectionFactory factory;

    private static final Logger logger = LoggerFactory.getLogger(RedisHealthIndicator.class);
}
