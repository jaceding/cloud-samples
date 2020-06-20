package per.jaceding.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.payment.vo.Result;

/**
 * 测试Nacos作为配置中心获取配置
 *
 * @RefreshScope 用于保证动态刷新
 *
 * @author jaceding
 * @date 2020/6/19
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public Result getConfigInfo() {
        return Result.success(configInfo);
    }
}
