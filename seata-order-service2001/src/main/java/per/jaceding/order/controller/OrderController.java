package per.jaceding.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.order.domain.Order;
import per.jaceding.order.service.OrderService;
import per.jaceding.payment.vo.Result;

/**
 * 订单控制层
 *
 * @author jaceding
 * @date 2020/6/22
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result createOrder(@RequestBody Order order) {
        orderService.create(order);
        return Result.success("创建订单成功");
    }

    @PostMapping("/testTs")
    public Result testTs(@RequestBody Order order){
        orderService.testTs(order);
        return Result.success("创建订单成功");
    }
}
