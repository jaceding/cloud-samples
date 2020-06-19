package per.jaceding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 主类
 *
 * 集成Bus后，不需要对请求所有客户端刷新配置，而是请求配置中心，配置中心会通知客户端刷新
 * 通知所有客户端 POST http://127.0.0.1:3344/actuator/bus-refresh
 * 通知config-client3355 POST http://127.0.0.1:3344/actuator/bus-refresh/config-client3355
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
