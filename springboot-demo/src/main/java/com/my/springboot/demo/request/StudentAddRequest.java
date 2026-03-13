package com.my.springboot.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentAddRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long classId;
}
