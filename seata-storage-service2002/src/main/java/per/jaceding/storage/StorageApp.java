package per.jaceding.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/22
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class StorageApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(StorageApp.class, args);
    }
}
