package per.jaceding.payment.service;


import com.baomidou.mybatisplus.extension.service.IService;
import per.jaceding.payment.entity.Payment;

/**
 * 支付业务层接口
 *
 * @author jaceding
 * @date 2020/6/16
 */
public interface PaymentService extends IService<Payment> {

    /**
     * 测试 Hystrix
     */
    String testHystrix();

    /**
     * 测试 Hystrix 异常处理
     */
    String testHystrixError();

    /**
     * 测试 Hystrix 服务熔断
     *
     * @param id 参数
     */
    String testHystrixCircuitBreaker(Integer id);
}
