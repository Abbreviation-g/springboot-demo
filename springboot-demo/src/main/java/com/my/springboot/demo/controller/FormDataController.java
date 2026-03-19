package com.my.springboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.my.springboot.demo.aop.OperationLogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class FormDataController {

    @OperationLogAnnotation(operationModel = "文件上传", operationType = "上传", operationDesc = "接收一个文件字节数组和两个字符串参数")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public JSONObject handleUpload(
            @RequestParam("byteData") MultipartFile byteData, // 接收字节数组作为文件
            @RequestParam("stringParam1") String stringParam1, // 接收字符串1
            @RequestParam("stringParam2") String stringParam2  // 接收字符串2
    ) throws IOException {
        byte[] bytes = byteData.getBytes();
        log.info("Received stringParam1: {}", stringParam1);
        log.info("Received stringParam2: {}", stringParam2);
        log.info("Received byte array length: {}", bytes.length);

        String parsedStr = new String(bytes, StandardCharsets.UTF_8);
        JSONObject result = new JSONObject();
        result.put("stringParam1", stringParam1);
        result.put("stringParam2", stringParam2);
        result.put("parsedStr", parsedStr);
        return result;
    }
}
