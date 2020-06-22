package per.jaceding.order.serivce;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import per.jaceding.payment.vo.Result;

/**
 * OpenFeign 整合
 *
 * @author jaceding
 * @date 2020/6/22
 */
@FeignClient(value = "payment-service", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/payment/{id}")
    Result getOne(@PathVariable("id") Long id);
}
