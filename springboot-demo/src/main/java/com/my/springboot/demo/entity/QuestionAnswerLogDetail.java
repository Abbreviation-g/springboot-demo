package com.my.springboot.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QuestionAnswerLogDetail implements Serializable {
    /**
     * requestId 主键
     */
    private String requestId;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 核心业务卡片列表 (用于前端突出展示事项入口)
     */
    private String serviceCards;
    /**
     * 文本引用来源列表 (对应文中的 [1][2]...)
     */
    private String references;
    /**
     * 澄清选项列表 (当意图不明确时提供)
     */
    private String options;
    /**
     * 推荐问题列表 (回答后自动生成)
     */
    private String suggestions;

    /**
     * 请求时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime requestTime;

    @JsonIgnore
    private Boolean deleted;
    private Boolean deletedByUser;
}
