package per.jaceding.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/17
 */
@EnableFeignClients
@EnableDiscoveryClient
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
