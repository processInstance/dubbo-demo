package com.css.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.css.util.SessionUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.css.api.UserService;

/**
 * @author : lin
 * @date : 15:19 2022/1/11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    UserService userService;

    SessionUtil sessionUtil = new SessionUtil();

    @GetMapping("/login")
    @SneakyThrows
    public String login(@RequestParam String userName) {
        String res = userService.login(userName);
        sessionUtil.setUser(res);
        return "login----" + res;
    }

    @GetMapping("/getUserMotion")
    @SneakyThrows
    public String getUser() {
        String res = userService.getUserMotion();
        Object user = sessionUtil.getUser();
        System.out.println("sessionUser" + user);
        System.out.println("getCurrentUser" + res);
        return "getCurrentUser----" + res;
    }

}
