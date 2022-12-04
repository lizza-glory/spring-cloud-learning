package com.lizza.order.controller;

import com.lizza.order.api.OrderService;
import com.lizza.order.entity.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("order-service")
public class OrderController implements OrderService {

    @Override
    public List<Order> getOrders() {
        return LongStream
                .range(1, 10)
                .boxed()
                .map(e -> Order.builder().id(e).build())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String timeout() {
        try { Thread.sleep(2000); } catch (Exception e) { e.printStackTrace(); }
        return "timeout";
    }
}
