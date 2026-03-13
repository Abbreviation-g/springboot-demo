package com.my.springboot.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.sql.Statement;

@Slf4j
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "update",
                args = {Statement.class}
        )
})
public class SqlInterceptor implements Interceptor, ApplicationContextAware {

    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = null;
        try {
            // 2. 执行原方法（继续SQL执行流程）
            result = invocation.proceed();
            return result;
        } finally {
            if (result != null) {
                StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
                // sql
                BoundSql boundSql = statementHandler.getBoundSql();

                saveOptLog(boundSql);
            }
        }
    }

    private void saveOptLog(BoundSql boundSql) {
        log.info("sql: {}", boundSql.getSql());
        log.info("params: {}", boundSql.getParameterMappings());
        //TODO
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
