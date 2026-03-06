package com.my.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class ThymeleafEndpointController {

    /**
     * 跳转到websocketDemo.html页面，携带自定义的cid信息。
     * http://localhost:8763/html/media_record
     *
     * @param model
     * @return
     */
    @GetMapping("/{name}")
    public String toWebSocketDemo(@PathVariable String name, Model model) {
        model.addAttribute("time", System.currentTimeMillis());
        return name;
    }
}
