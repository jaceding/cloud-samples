package per.jaceding.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.payment.vo.Result;

/**
 * 测试从配置中心获取配置
 * @RefreshScope + 暴露端点后，还需要发起一次POST请求到服务器才能刷新配置
 * http://127.0.0.1:3355/actuator/refresh
 *
 * @author jaceding
 * @date 2020/6/19
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public Result getConfigInfo() {
        return Result.success(configInfo);
    }
}
