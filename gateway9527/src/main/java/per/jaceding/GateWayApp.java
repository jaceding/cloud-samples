package per.jaceding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/19
 */
@EnableEurekaClient
@SpringBootApplication
public class GateWayApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayApp.class, args);
    }
}
