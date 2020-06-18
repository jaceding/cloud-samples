package per.jaceding.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import per.jaceding.order.config.RibbonConfiguration;
import per.jaceding.payment.annotation.AvoidScan;

/**
 * 主类
 *
 * ComponentScan excludeFilters 表示让工程在启动的时候，不让Spring扫描被@AvoidScan注解标记的类
 * 因为配置的是针对特殊服务的负载策略，不是全局的，如果不排除，启动就会报错
 *
 * RibbonClient name针对哪个服务采用RibbonConfiguration中的策划
 *
 * @author jaceding
 * @date 2020/6/17
 */
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {AvoidScan.class})})
@RibbonClients(value = {
        @RibbonClient(name = "payment-service", configuration = RibbonConfiguration.class)
})
@EnableEurekaClient
@SpringBootApplication
public class OrderApp {

    /**
     * 主方法
     *
     * @param args 启动
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

}
