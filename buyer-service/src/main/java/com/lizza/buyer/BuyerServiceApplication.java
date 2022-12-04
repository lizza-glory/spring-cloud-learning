package com.lizza.buyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.lizza"})
@SpringBootApplication
public class BuyerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyerServiceApplication.class, args);
    }
}
