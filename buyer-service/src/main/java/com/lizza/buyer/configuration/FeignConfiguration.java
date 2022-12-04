package com.lizza.buyer.configuration;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    public static int connectTimeOutMillis = 10000;
    public static int readTimeOutMillis = 10000;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
}
