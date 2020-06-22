package per.jaceding.payment.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.payment.vo.Result;

/**
 * Sentinel 限流测试
 * <p>
 * 默认流控规则是临时的，服务重启会规则会被清除
 *
 * @author jaceding
 * @date 2020/6/20
 */
@Slf4j
@RestController
public class LimitController {

    @GetMapping("/byResouce")
    @SentinelResource(value = "byResouce", blockHandler = "byResourceHandler")
    public Result byResource() {
        return Result.success("byResource");
    }

    public Result byResourceHandler(BlockException blockException) {
        log.error("byResourceHandler", blockException);
        return Result.success("byResourceHandler");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public Result byUrl() {
        return Result.success("byUrl");
    }

    /**
     * 自定义降级处理类
     */
    @GetMapping("/customHandler")
    @SentinelResource(value = "customHandler",blockHandlerClass = CustomBlockHandler.class,blockHandler = "handler1")
    public Result customHandler() {
        return Result.success("customHandler");
    }
}
