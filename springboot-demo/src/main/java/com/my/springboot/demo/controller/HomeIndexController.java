package com.my.springboot.demo.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping
public class HomeIndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);

            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

            JSONObject result = new JSONObject();
            result.put(tokenInfo.getTokenName(), tokenInfo.getTokenValue());

            return result;
        }
        JSONObject result = new JSONObject();
        result.put("code", 401);
        result.put("msg", "登录失败");
        return result;
    }

    @GetMapping("/isLogin")
    @ResponseBody
    public String isLogin() {
        boolean isLogin = StpUtil.isLogin();
        log.info("当前会话是否登录：{}", isLogin);
        log.info("当前登录id：{}", StpUtil.getLoginId());
        if (!isLogin) {
            return "当前会话未登录";
        } else {
            return "当前会话已登录";
        }
    }
}
