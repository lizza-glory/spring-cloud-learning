package com.lizza.buyer.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class BackdoorController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("hello")
    public String hello() {
        return "hello " + applicationName;
    }

    @GetMapping("getInetInfo")
    public Map<String, Object> getInetInfo() {
        Map<String, Object> result = new HashMap<>();

        InetUtils inetUtils = new InetUtils(new InetUtilsProperties());
        result.put("HostInfo", JSONObject.toJSONString(inetUtils.findFirstNonLoopbackHostInfo()));
        result.put("InetAddress", JSONObject.toJSONString(inetUtils.findFirstNonLoopbackAddress()));

        return result;
    }
}
