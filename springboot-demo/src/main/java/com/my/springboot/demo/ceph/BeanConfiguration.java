package com.my.springboot.demo.ceph;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return createIgnoreSSLRestTemplate();
    }

    public static RestTemplate createIgnoreSSLRestTemplate() {
        RestTemplate restTemplate = null;
        try {
            restTemplate = new RestTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restTemplate;
    }
}
