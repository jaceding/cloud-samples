# cloud-samples
Samples for Spring Cloud

## 目录

* [cloud\-samples](#cloud-samples)
  * [目录](#%E7%9B%AE%E5%BD%95)
  * [公共模块](#%E5%85%AC%E5%85%B1%E6%A8%A1%E5%9D%97)
  * [eureka集群作为注册中心](#eureka%E9%9B%86%E7%BE%A4%E4%BD%9C%E4%B8%BA%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83)
  * [zookeeper集群作为注册中心](#zookeeper%E9%9B%86%E7%BE%A4%E4%BD%9C%E4%B8%BA%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83)
  * [consul作为注册中心](#consul%E4%BD%9C%E4%B8%BA%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83)
  * [整合Ribbon](#%E6%95%B4%E5%90%88ribbon)
  * [整合OpenFeign](#%E6%95%B4%E5%90%88openfeign)
  * [整合Hystrix](#%E6%95%B4%E5%90%88hystrix)
  * [整合Gateway](#%E6%95%B4%E5%90%88gateway)
  * [整合Config、Bus](#%E6%95%B4%E5%90%88configbus)
  * [整合Nacos](#%E6%95%B4%E5%90%88nacos)
  * [整合Sentinel](#%E6%95%B4%E5%90%88sentinel)
  * [整合Seata](#%E6%95%B4%E5%90%88seata)

## 公共模块

1. api-common

## eureka集群作为注册中心

1. consumer-order80
2. provider-payment8001
3. provider-payment8002
4. eureka-server7001
5. eureka-server7002

## zookeeper集群作为注册中心

1. consumer-order84
2. provider-payment8004

## consul作为注册中心

1. consumer-order86
2. provider-payment8006

## 整合Ribbon

**自定义负载均衡策略**

1. consumer-order81
2. provider-payment8001
3. provider-payment8002
4. eureka-server7001
5. eureka-server7002

## 整合OpenFeign

**实现了服务调用、超时控制、日志增强**

1. consumer-order82
2. provider-payment8001
3. provider-payment8002
4. eureka-server7001
5. eureka-server7002

## 整合Hystrix

**实现了服务降级、服务熔断、服务监控**

1. consumer-order88
2. provider-payment8008
3. hystrix-dashboard9001
4. eureka-server7001
5. eureka-server7002

## 整合Gateway

**实现了网关、动态路由配置、自定义Filter打印日志及检查id是否合法**

1. provider-payment8001
2. provider-payment8002
3. gateway9527
4. eureka-server7001
5. eureka-server7002

## 整合Config、Bus

**实现配置中心、配置动态刷新(需要手动发送POST请求给客户端)**

**集成Bus后，动态刷新配置（只需要手动发送POST请求给配置中心）、刷新指定客户端的配置**

注意：上述中的客户端是相对配置中心来说的，集成Bus后可以通过GitHub或者GitLab的webhook功能自动发送POST请求。

1. config-center3344
2. config-client3355
3. config-client3356

## 整合Nacos

**实现注册中心、配置中心（动态刷新配置）**

1. provider-payment9001
2. provider-payment9002
3. consumer-order91
4. config-nacos-client3366

## 整合Sentinel

**实现流控、服务降级、服务熔断、服务监控、整合openfeign、配置持久化**

1. provider-payment9004
2. provider-payment9005
3. consumer-order92

## 整合Seata

**与mybatis plus整合，实现分布式事务**

1. seata-order-service2001
2. seata-storage-service2002
3. seata-account-service2003