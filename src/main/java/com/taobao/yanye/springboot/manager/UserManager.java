package com.taobao.yanye.springboot.manager;

import com.taobao.yanye.springboot.dal.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by yanye on 17-2-18.
 */
public interface UserManager {
    User findOne(Long userId);

    Long insert(User user);

    List<User> listAll(int currentPage, int pageSize);
}
