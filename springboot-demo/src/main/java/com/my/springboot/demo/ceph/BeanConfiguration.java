package com.my.springboot.demo.ceph;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class BeanConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return createIgnoreSSLRestTemplate();
    }

    public static RestTemplate createIgnoreSSLRestTemplate() {
        RestTemplate restTemplate = null;
        try {
            TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            HttpClientBuilder clientBuilder = HttpClients.custom();
            CloseableHttpClient httpClient = clientBuilder.setConnectionManager(PoolingHttpClientConnectionManagerBuilder
                    .create().setSSLSocketFactory(sslsf).build()).build();
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectionRequestTimeout(30000);
            httpRequestFactory.setConnectTimeout(30000);
            httpRequestFactory.setHttpClient(httpClient);
            restTemplate = new RestTemplate(httpRequestFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restTemplate;
    }
}
