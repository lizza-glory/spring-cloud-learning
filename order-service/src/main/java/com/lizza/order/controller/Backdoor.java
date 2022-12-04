package com.lizza.order.controller;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping
@RestController
public class Backdoor {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private SpringClientFactory clientFactory;

    @GetMapping("hello")
    public String hello() {
        return "hello, " + applicationName;
    }

    /**
     * 通过 DiscoveryClient 获取注册中心信息
     * @return
     */
    @GetMapping("getRegistryInfo")
    public Map<String, Object> getRegistryInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("description", discoveryClient.description());
        List<String> services = discoveryClient.getServices();
        Map<String, Object> serviceMap = new HashMap<>();
        for (String service : services) {
            serviceMap.put(service, discoveryClient.getInstances(service));
        }
        map.put("services", serviceMap);
        return map;
    }

    /**
     * 根据 serverId 获取负载均衡策略
     * @param serverId
     * @return
     */
    @GetMapping("getRule")
    public String getRule(String serverId) {
        return ((ZoneAwareLoadBalancer<?>) clientFactory
                .getLoadBalancer(serverId))
                .getRule()
                .getClass()
                .getName();
    }

    @GetMapping("changeRobbinRule")
    public void changeRobbinRule(@RequestParam String key, @RequestParam String serverId) {
        // IRule 集合
        Map<String, IRule> map = new HashMap<>();
        map.put(RandomRule.class.getSimpleName(), new RandomRule());
        map.put(RoundRobinRule.class.getSimpleName(), new RoundRobinRule());
        IRule rule = map.get(key);

        // 设置自定义的负载均衡策略
        ((BaseLoadBalancer) clientFactory.getLoadBalancer(serverId)).setRule(rule);
    }
}
