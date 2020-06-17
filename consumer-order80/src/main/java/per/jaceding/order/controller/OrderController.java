package per.jaceding.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public static final String CREATE_PAYMENT_URL = "http://provider-payment8001/payment/";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Result create() {
        try {
            String serial = UUID.randomUUID().toString();
            log.info("serial#" + serial);
            Map<String, String> body = new HashMap<>(2, 1f);
            body.put("serial", UUID.randomUUID().toString());
            log.info("CREATE_PAYMENT_URL#" + CREATE_PAYMENT_URL);
            Result result = restTemplate.postForObject(CREATE_PAYMENT_URL, body, Result.class);
            log.info("result#" + result.toString());
            if (result != null && result.getCode() == 200) {
                return Result.success("创建成功", body);
            }
            log.info("创建成功");
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return Result.fail("创建失败");
    }
}
