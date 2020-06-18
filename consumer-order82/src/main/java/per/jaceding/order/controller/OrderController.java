package per.jaceding.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jaceding.order.service.PaymentService;
import per.jaceding.order.vo.PaymentVO;
import per.jaceding.payment.vo.Result;

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

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Result create() {
        try {
            String serial = UUID.randomUUID().toString();
            log.info("serial#" + serial);
            PaymentVO paymentVO = new PaymentVO();
            paymentVO.setSerial(serial);
            Result result = paymentService.create(paymentVO);
            log.info("result#" + result.toString());
            return Result.success(result.getData());
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return Result.fail("创建失败");
    }

    @GetMapping("/payment/{id}")
    public Result get(@PathVariable("id") Long id) {
        try {
            Result result = paymentService.getOne(id);
            log.info("result#" + result.toString());
            return result;
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }
}
