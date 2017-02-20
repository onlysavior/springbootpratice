package com.taobao.yanye.springboot.manager.impl;

import com.taobao.yanye.springboot.dal.UserRepository;
import com.taobao.yanye.springboot.dal.model.User;
import com.taobao.yanye.springboot.manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yanye on 17-2-18.
 */
@Component
public class UserManagerImp implements UserManager {
    @Autowired
    UserRepository userRepository;

    @Cacheable("user")
    public User findOne(Long userId) {
        logger.info("doFindOne, userId: " + userId);
        return userRepository.findOne(userId);
    }

    public Long insert(User user) {
        userRepository.insert(user);
        return user.getId();
    }

    public List<User> listAll(int currentPage,
                              int pageSize) {
        int startPos = currentPage > 1 ? (currentPage - 1) * pageSize : 0;
        return userRepository.query(startPos, pageSize);
    }

    private static final Logger logger = LoggerFactory.getLogger(UserManagerImp.class);
}
