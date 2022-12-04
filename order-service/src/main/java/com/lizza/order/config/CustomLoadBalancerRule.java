package com.lizza.order.config;

import com.lizza.base.common.tracer.Tracer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class CustomLoadBalancerRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = getLoadBalancer();
        // 服务提供者分组
        Map<String, List<Server>> serverMap = new HashMap<>();

        for (Server server : loadBalancer.getAllServers()) {
            InstanceInfo instanceInfo = ((DiscoveryEnabledServer) server).getInstanceInfo();
            // 服务提供者根据 server-type/group-name 分组
            String group = instanceInfo.getAppGroupName();
            List<Server> list = serverMap.getOrDefault(group, new ArrayList<>());
            list.add(server);
            serverMap.put(group, list);
        }


        int count = 0;
        Server server = null;
        while (server == null || count++ < 10) {
            // 根据请求类型获取服务提供者列表
            List<Server> list = serverMap.get(Tracer.getServerType());
            // 使用线程安全的 ThreadLocalRandom, 随机选取一个服务提供者
            int index = ThreadLocalRandom.current().nextInt(list.size());
            server = list.get(index);
            if (server == null) {
                Thread.yield();
                return null;
            }
            if (server.isAlive() && server.isReadyToServe()) {
                return server;
            }
        }
        return null;
    }



    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
