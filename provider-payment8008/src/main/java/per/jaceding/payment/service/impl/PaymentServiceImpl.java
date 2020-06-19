package per.jaceding.payment.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import per.jaceding.payment.dao.PaymentMapper;
import per.jaceding.payment.entity.Payment;
import per.jaceding.payment.service.PaymentService;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 支付业务层实现
 *
 * @author jaceding
 * @date 2020/6/16
 */
@Slf4j
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Override
    public String testHystrix() {
        String msg = "线程：" + Thread.currentThread().getName() + "\t testHystrix";
        log.info("msg#" + msg);
        return msg;
    }

    /**
     * 方法中发生任何异常都会走testHystrixErrorHandler方法进行降级处理
     *
     * 设置 @HystrixProperty execution.isolation.thread.timeoutInMilliseconds 等于 2000
     * 目的是让程序等待超过2000ms后就当成异常处理
     */
    @HystrixCommand(fallbackMethod = "testHystrixErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public String testHystrixError() {
        try {
//            int num = 10 / 0;
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String msg = "线程：" + Thread.currentThread().getName() + "\t testHystrixError";
        log.info("msg#" + msg);
        return msg;
    }

    /**
     * testHystrixError服务降级方案
     */
    public String testHystrixErrorHandler() {
        String msg = "线程：" + Thread.currentThread().getName() + "\t testHystrixErrorHandler: 系统繁忙或运行报错";
        log.info("msg#" + msg);
        return msg;
    }
}
