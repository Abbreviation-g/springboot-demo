package com.my.springboot.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.my.springboot.demo.dao.QaLogDetailMapper;
import com.my.springboot.demo.entity.QuestionAnswerLogDetail;
import com.my.springboot.demo.request.LogDetailRequest;
import com.my.springboot.demo.response.PageItemsResponse;
import com.my.springboot.demo.response.QaLogDetailResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 问答前端界面的历史对话
 */
@Slf4j
@RestController
@RequestMapping("/inference/log")
public class InferenceLogController {
    @Resource
    private QaLogDetailMapper qaLogDetailMapper;

    /**
     * 根据sessionId和requestId查询历史对话详情,
     * 如果requestId不存在，则返回sessionId对应的所有对话详情并根据pageNo, pageSize进行分页
     * 如果requestId存在，则返回requestId所在页数据
     * requestId和pageNo, pageSize 二选一
     *
     * @return
     */
    @SaCheckPermission(value = {"log:query"})
    @GetMapping("/detail")
    public PageItemsResponse<QaLogDetailResponse> historyDetail(LogDetailRequest request) {
        String sessionId = request.getSessionId();
        String requestId = request.getRequestId();
        log.info("InferenceLogController.historyDetail,sessionId={}, requestId={}", sessionId, requestId);

        int total = qaLogDetailMapper.total(sessionId);
        int pageSize = request.getPageSize();
        int current;
        int offset;
        if (ObjectUtils.isEmpty(requestId)) {
            current = request.getPageNo();
            offset = (current - 1) * pageSize;
        } else {
            int index = qaLogDetailMapper.totalBeforeRequestId(requestId, sessionId);
            current = index / pageSize + 1;
            offset = (index / pageSize) * pageSize;
        }
        List<QuestionAnswerLogDetail> currentPageItems = qaLogDetailMapper.queryByPage(sessionId, offset, pageSize);
        List<QaLogDetailResponse> items = currentPageItems.stream().map(QaLogDetailResponse::new).toList();
        return new PageItemsResponse<>(items, total, current, pageSize);
    }
}