package per.jaceding.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.jaceding.payment.entity.Payment;
import per.jaceding.payment.service.PaymentService;
import per.jaceding.payment.vo.Result;

/**
 * 支付 控制层
 *
 * @author jaceding
 * @date 2020/6/16
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 创建支付信息
     */
    @PostMapping
    public Result create(@RequestBody Payment payment) {
        try {
            if (paymentService.save(payment)) {
                return Result.success();
            }
            log.info("创建成功");
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return Result.fail("创建失败");
    }

    /**
     * 查询支付信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Long id) {
        try {
            Payment payment = paymentService.getById(id);
            if (payment == null) {
                return Result.fail("查询失败，id:" + id);
            }
            return Result.success(payment);
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }
}
