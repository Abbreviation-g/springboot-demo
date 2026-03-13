package com.my.springboot.demo.config;

import com.my.springboot.demo.utils.AESUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyBatisEncryptionInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisEncryptionInterceptor.class);

    private Object[] getParameter(Invocation invocation) {
        return invocation.getArgs();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();

        // UPDATE/INSERT 操作：加密输入参数
        if ("update".equals(methodName)) {
            Object[] parameters = getParameter(invocation);
            for (Object parameter : parameters) {
                encryptFields(parameter);
            }
        }

        // 执行原始SQL
        Object result = invocation.proceed();

        // SELECT 操作：解密查询结果
        if ("query".equals(methodName)) {
            decryptResult(result);
        }

        return result;
    }

    private void encryptFields(Object obj) {
        if (obj == null) {
            return;
        }

        // 只处理实体对象，不处理基本类型和Map
        if (ClassUtils.isPrimitiveOrWrapper(obj.getClass()) || obj instanceof Map || obj instanceof Collection) {
            return;
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Encrypted.class)) {
                continue;
            }
            if (!field.getType().equals(String.class)) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof String) {
                    String encrypted = AESUtil.encrypt((String) value);
                    field.set(obj, encrypted);
                }
            } catch (Exception e) {
                logger.error("加密字段失败: {}", field.getName(), e);
            }
        }
    }

    private void decryptResult(Object result) {
        if (result instanceof Iterable) {
            for (Object item : (Iterable<?>) result) {
                decryptFields(item);
            }
        } else if (result != null) {
            decryptFields(result);
        }
    }

    private void decryptFields(Object obj) {
        if (obj == null) {
            return;
        }
        // 解密逻辑和加密类似，但是反向操作
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Encrypted.class)) {
                continue;
            }
            if (!field.getType().equals(String.class)) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object value = field.get(obj);

                if (value instanceof String) {
                    String decrypted = AESUtil.decrypt((String) value);
                    field.set(obj, decrypted);
                }
            } catch (Exception e) {
                logger.error("解密字段失败: {}", field.getName(), e);
            }
        }
    }
}
