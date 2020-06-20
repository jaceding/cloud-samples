package per.jaceding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/19
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApp.class, args);
    }
}
