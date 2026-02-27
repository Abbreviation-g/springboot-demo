package com.my.springboot.demo.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.SaTokenException;
import com.my.springboot.demo.utils.BusinessException;
import com.my.springboot.demo.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result<String> bizExceptionHandler(BusinessException e) {
        logger.error("业务异常", e);
        return Result.businessFailed(e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<String> notValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("请求参数校验异常！原因是：{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Optional<String> optional = bindingResult.getFieldErrors().stream()
                .map(err -> err.getField() + " " + err.getDefaultMessage()).reduce((s, s2) -> s + ";" + s2);
        return optional.map(Result::failed).orElseGet(() -> Result.failed(""));
    }

    /**
     * 处理数据库数据重复异常
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result<String> duplicateKeyException(DuplicateKeyException e) {
        logger.error("sql unique key 约束错误: ", e);
        String message = null;
        if (e.getCause() != null) {
            message = e.getCause().getMessage();
        } else {
            message = e.getMessage();
        }
        message = "数据重复. " + message;
        return Result.failed(message);
    }

    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public Result<String> notLoginExceptionHandler(NotLoginException e) {
        logger.error("用户未登陆, {}", e.getMessage());
        return Result.failed(HttpStatus.UNAUTHORIZED.value(), "用户未登录");
    }

    @ExceptionHandler(value = NotPermissionException.class)
    @ResponseBody
    public Result<String> notPermissionExceptionHandler(NotPermissionException e) {
        logger.error("用户无此权限, {}", e.getMessage());
        return Result.failed(HttpStatus.UNAUTHORIZED.value(), "无此权限：" + e.getPermission());
    }

    @ExceptionHandler(value = SaTokenException.class)
    @ResponseBody
    public Result<String> notPermissionExceptionHandler(SaTokenException e) {
        logger.error("satoken无效, {}", e.getMessage());
        return Result.failed(HttpStatus.UNAUTHORIZED.value(), "登录无效");
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e) {
        logger.error("其他异常", e);
        return Result.failed(e.getMessage());
    }
}

