package per.jaceding.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import per.jaceding.order.serivce.PaymentService;
import per.jaceding.payment.vo.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 订单 控制层
 * <p>
 * 总结：
 * fallback只负责业务中的异常处理
 * blockHandler只负责sentinel控制台配置的违规处理
 * 若fallback和blockHandler都配置，则被限流降级而抛出BlockException时只会进入blockHandler进行处理
 *
 * @author jaceding
 * @date 2020/6/17
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {


    @Value("${service-url.payment-service}")
    private String PAYMENT_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Result create() {
        try {
            String serial = UUID.randomUUID().toString();
            log.info("serial#" + serial);
            Map<String, String> body = new HashMap<>(2, 1f);
            body.put("serial", UUID.randomUUID().toString());
            log.info("PAYMENT_URL#" + PAYMENT_URL);
            Result result = restTemplate.postForObject(PAYMENT_URL, body, Result.class);
            log.info("result#" + result.toString());
            return result;
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return Result.fail("创建失败");
    }

    @GetMapping("/payment/{id}")
    public Result get(@PathVariable("id") Long id) {
        try {
            log.info("PAYMENT_URL#" + PAYMENT_URL);
            Result result = restTemplate.getForObject(PAYMENT_URL + "/" + id, Result.class);
            log.info("result#" + result.toString());
            return result;
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }

    /**
     * fallback 测试
     */
    @GetMapping("/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", exceptionsToIgnore = {IllegalArgumentException.class})
    public Result testFallBack(@PathVariable("id") Long id) {
        log.info("PAYMENT_URL#" + PAYMENT_URL);
        if (id < 1) {
            throw new IllegalArgumentException("id不能小于1");
        }
        Result result = restTemplate.getForObject(PAYMENT_URL + "/" + id, Result.class);
        if (result.getData() == null) {
            throw new NullPointerException("result结果为空");
        }
        log.info("result#" + result.toString());
        return result;
    }

    public Result handlerFallback(@PathVariable("id") Long id, Throwable e) {
        log.error("handlerFallback", e);
        return Result.fail(e.getMessage());
    }

    /**
     * sentinel 整合 openfeign
     */
    @GetMapping("/openfeign/{id}")
    public Result testOpenFeign(@PathVariable("id") Long id) {
        return paymentService.getOne(id);
    }
}
