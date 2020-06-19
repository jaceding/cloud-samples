package per.jaceding.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import per.jaceding.payment.vo.Result;

/**
 * 支付业务层接口
 *
 * @author jaceding
 * @date 2020/6/18
 */
@FeignClient(value = "payment-service")
public interface PaymentService {

    /**
     * 测试 Hystrix
     */
    @GetMapping("/payment/testHystrix")
    Result testHystrix();

    /**
     * 测试 Hystrix 异常处理
     */
    @GetMapping("/payment/testHystrixError")
    Result testHystrixError();
}
