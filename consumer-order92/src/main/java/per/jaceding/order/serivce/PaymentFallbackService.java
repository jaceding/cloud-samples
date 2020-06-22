package per.jaceding.order.serivce;

import org.springframework.stereotype.Component;
import per.jaceding.payment.vo.Result;

/**
 * openfeign fallback整合sentinel
 *
 * @author jaceding
 * @date 2020/6/22
 */
@Component
public class PaymentFallbackService implements PaymentService{

    @Override
    public Result getOne(Long id) {
        return Result.fail("服务降级 --- PaymentFallbackService");
    }
}
