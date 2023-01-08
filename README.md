# Spring Cloud 学习笔记
项目场景模拟电商平台
## 模块梳理
- base-common: 公共模块, 比如用到的常量, 工具类都在这个模块下
- base-eureka: 服务注册中心服务, 单独部署
- buyer-service: 买家服务, 买家个人的信息, 订单信息, 购物车等, 由此聚合
- order-service: 订单服务
- order-service-api: 订单服务 api
- payment-service: 支付服务

## 项目所用技术栈梳理

### Open Feign
- buyer-service