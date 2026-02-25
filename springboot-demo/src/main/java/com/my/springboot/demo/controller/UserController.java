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
}
