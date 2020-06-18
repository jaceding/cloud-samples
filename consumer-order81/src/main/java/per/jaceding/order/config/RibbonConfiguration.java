package per.jaceding.order.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.jaceding.order.loadbalancer.MyRoundRule;
import per.jaceding.payment.annotation.AvoidScan;

/**
 * Ribbon配置类
 *
 * @author jaceding
 * @date 2020/6/18
 */
@AvoidScan
@Configuration
public class RibbonConfiguration {

    /**
     * 注入 RandomRule
     */
    @Bean
    public IRule ribbonRule() {
        return new MyRoundRule();
    }
}
