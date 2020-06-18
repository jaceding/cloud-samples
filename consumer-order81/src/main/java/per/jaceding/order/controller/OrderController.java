package per.jaceding.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import per.jaceding.payment.vo.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 订单 控制层
 *
 * @author jaceding
 * @date 2020/6/17
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 支付模块地址
     */
    public static final String PAYMENT_URL = "http://payment-service/payment/";

    @Autowired
    private RestTemplate restTemplate;

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
            Result result = restTemplate.getForObject(PAYMENT_URL + id, Result.class);
            log.info("result#" + result.toString());
            return result;
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/test/{id}")
    public Result testTemplate(@PathVariable("id") Long id){
        try{
            log.info("PAYMENT_URL#" + PAYMENT_URL);
            ResponseEntity<Result> responseEntity = restTemplate.getForEntity(PAYMENT_URL + id, Result.class);
            log.info("result#" + responseEntity.toString());
            return Result.success(responseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("test");
    }
}
