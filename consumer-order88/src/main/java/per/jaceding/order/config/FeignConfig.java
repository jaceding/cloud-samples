package per.jaceding.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置类
 *
 * @author jaceding
 * @date 2020/6/18
 */
@Configuration
public class FeignConfig {

    /**
     * 注入Feign Level
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
