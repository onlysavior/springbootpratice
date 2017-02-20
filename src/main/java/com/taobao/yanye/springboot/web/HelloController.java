package com.taobao.yanye.springboot.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.yanye.springboot.dal.model.User;
import com.taobao.yanye.springboot.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yanye on 17-2-18.
 */
@RestController
@RequestMapping(value = "/api", produces = {"application/json; charset=UTF-8"})
public class HelloController {

    @RequestMapping("/home")
    public String home() {
        return "HELLO WORLD";
    }

    @RequestMapping("/get/{id}")
    public User getById(@PathVariable("id") Long userId) {
        return userManager.findOne(userId);
    }

    @RequestMapping("/all/{currentPage}/{pageSize}")
    public List<User> getPage(@PathVariable("currentPage") Integer currentPage,
                              @PathVariable("pageSize") Integer pageSize) {
        return userManager.listAll(currentPage, pageSize);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Long save(@RequestBody User user) {
        return userManager.insert(user);
    }

    @RequestMapping(value = "/save/{user}")
    public Long saveGet(@PathVariable("user") String user) {
        User user1 = new User();
        user1.setName(user);
        user1.setPassword(user);

        return userManager.insert(user1);
    }

    @Autowired
    UserManager userManager;
}
