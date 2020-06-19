package per.jaceding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 主类
 *
 * @author jaceding
 * @date 2020/6/19
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigCenterApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApp.class, args);
    }
}
