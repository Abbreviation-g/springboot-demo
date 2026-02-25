package com.my.springboot.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LogDetailRequest {
    @NotBlank
    private String sessionId;
    /**
     * 请求id如果存在，那么找到当前requestId所在页，
     * 如果不存在，那么按照pageNo 进行分页
     * requestId 和 pageNo二选一
     */
    private String requestId;
    private Integer pageNo;
    private Integer pageSize = 10;
}
