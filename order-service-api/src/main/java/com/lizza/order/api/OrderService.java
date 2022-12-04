package com.lizza.order.api;

import com.lizza.order.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "ORDER-SERVICE",
        path = "/order-service")
public interface OrderService {

    @GetMapping("getOrders")
    List<Order> getOrders();

    // 模拟 feign timeout
    @GetMapping("timeout")
    String timeout();
}
