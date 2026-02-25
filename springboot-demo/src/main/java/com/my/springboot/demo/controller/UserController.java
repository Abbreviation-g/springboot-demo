package com.my.springboot.demo.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/show")
    public String showUser() {
        redisTemplate.opsForValue().set("user", "这是学生信息");
        log.info("这是学生信息");
        log.info(redisTemplate.opsForValue().get("user").toString());
        return "这是学生信息";
    }

    // 测试登录，浏览器访问： http://localhost:8763/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8763/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
