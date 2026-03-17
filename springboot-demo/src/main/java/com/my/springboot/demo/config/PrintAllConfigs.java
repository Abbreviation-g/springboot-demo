package com.my.springboot.demo.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrintAllConfigs implements CommandLineRunner, ApplicationContextAware {

    @Resource
    private ConfigurableEnvironment environment;
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) {
        PrintAllConfigs bean = applicationContext.getBean(PrintAllConfigs.class);
        log.info("--- PrintAllConfigs Bean: {} ---", bean);
        log.info("--- All Environment Properties ---");
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            String propertySourceName = propertySource.getName();
            Object source = propertySource.getSource();
            if (source instanceof Iterable<?> iterablePropertySource) {
                printIterable(iterablePropertySource);
            } else {
                log.info("Property Source: {}, source: {}", propertySourceName, source);
            }
        }
        log.info("--- End of Properties ---\n");
    }

    private void printIterable(Iterable<?> iterable) {
        for (Object item : iterable) {
            if (item instanceof Iterable<?>) {
                printIterable((Iterable<?>) item);
            } else {
                if (item instanceof ConfigurationPropertyName configurationPropertyName) {
                    log.info("Property Name: {}, value: {}", configurationPropertyName, environment.getProperty(configurationPropertyName.toString()));
                } else {
                    log.info("item:{}, class:{}", item, item.getClass());
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}