package per.jaceding.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jaceding.order.service.PaymentService;
import per.jaceding.payment.vo.Result;

/**
 * 订单 控制层
 * DefaultProperties 全局配置（但需要配置HystrixCommand注解）
 *
 * @author jaceding
 * @date 2020/6/17
 */
@DefaultProperties(defaultFallback = "defaultHandler")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/testHystrix")
    public Result testHystrix() {
        Result result = paymentService.testHystrix();
        log.info("result#" + result.toString());
        return result;
    }

    @HystrixCommand(fallbackMethod = "testHystrixErrorHandler2", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/testHystrixError")
    public Result testHystrixError() {
        Result result = paymentService.testHystrixError();
        log.info("result#" + result.toString());
        return result;
    }

    public Result testHystrixErrorHandler2() {
        return Result.fail("系统繁忙，请稍后再试");
    }

    public Result defaultHandler(){
        return Result.fail("系统繁忙，请稍后再试");
    }
}
