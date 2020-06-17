package per.jaceding.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/17
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
