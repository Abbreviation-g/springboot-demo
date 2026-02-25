package com.my.springboot.demo.dao;

import com.my.springboot.demo.entity.QuestionAnswerLogDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QaLogDetailMapper {
    QuestionAnswerLogDetail selectByRequestId(String requestId, String sessionId);

    int totalBeforeRequestId(String requestId, String sessionId);

    int total(String sessionId);

    List<QuestionAnswerLogDetail> queryByPage(String sessionId, Integer offset, Integer limit);
}
