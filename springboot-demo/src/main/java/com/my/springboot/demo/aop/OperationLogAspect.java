package com.my.springboot.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置操作日志切入点,在注解的位置切入代码
     */
    @Pointcut("@annotation(com.my.springboot.demo.aop.OperationLogAnnotation)")
    public void operationLogPointCut() {
    }

    /**
     * 记录操作日志
     *
     * @param joinPoint 方法的执行点
     * @param result    方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "result", value = "operationLogPointCut()")
    public void saveOperationLog(JoinPoint joinPoint, Object result) throws Throwable {
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);

            String operationModel = annotation.operationModel();
            String operationType = annotation.operationType();
            String operationDesc = annotation.operationDesc();
            log.info("操作:{},操作类型:{},操作描述:{}", operationModel, operationType, operationDesc);
        } catch (Exception e) {
            log.error("操作日志记录异常", e);
        }
    }
}
