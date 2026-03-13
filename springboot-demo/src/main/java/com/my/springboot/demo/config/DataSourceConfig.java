package com.my.springboot.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    public static final String MAPPER_LOCATION = "classpath*:mapper/**/**/*Mapper*.xml";

    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory gysSqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        // 添加拦截器
        sessionFactoryBean.addPlugins(new SqlInterceptor());
        sessionFactoryBean.setPlugins(new MyBatisEncryptionInterceptor());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public JdbcTemplate accountjdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
