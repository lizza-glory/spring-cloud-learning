package com.lizza.buyer.controller;

import com.lizza.order.api.OrderService;
import com.lizza.order.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("getOrders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("timeout")
    public String timeout() {
        return orderService.timeout();
    }
}
