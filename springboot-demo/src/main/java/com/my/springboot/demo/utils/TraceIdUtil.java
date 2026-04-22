package com.my.springboot.demo.utils;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;

public class TraceIdUtil {
    public static final String TRACE_ID_KEY = "TraceId";

    public static String generateTraceId() {
        String traceId = IdUtil.fastSimpleUUID().toUpperCase();
        MDC.put(TRACE_ID_KEY, traceId);
        return traceId;
    }

    public static String generateTraceId(String traceId) {
        if (ObjectUtils.isEmpty(traceId)) {
            return generateTraceId();
        }
        MDC.put(TRACE_ID_KEY, traceId);
        return traceId;
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID_KEY);
    }
}
