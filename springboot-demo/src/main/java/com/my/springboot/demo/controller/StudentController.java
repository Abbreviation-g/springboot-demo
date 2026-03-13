package com.my.springboot.demo.controller;

import com.my.springboot.demo.dao.StudentMapper;
import com.my.springboot.demo.entity.Student;
import com.my.springboot.demo.request.StudentAddRequest;
import com.my.springboot.demo.utils.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentMapper studentMapper;
    @PutMapping("/update")
    public String update() {
        log.info("update student");
        return "update student";
    }

    @PostMapping("/add")
    public Result<String> add(@Valid @RequestBody StudentAddRequest request) {
        log.info("add student, request={}", request);
        Student student = new Student();
        student.setName(request.getName());
        student.setClassId(request.getClassId());
        int result = studentMapper.insert(student);
        log.info("add student, result={}", result);

        return Result.ok("add student success");
    }
}
