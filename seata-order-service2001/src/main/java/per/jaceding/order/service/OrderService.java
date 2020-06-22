package per.jaceding.order.service;

import per.jaceding.order.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单业务层接口
 *
 * @author  jaceding
 * @date    2020/6/22
 */
public interface OrderService extends IService<Order>{

    /**
     * 创建订单 测试全局事务
     *
     * @param order order
     */
    void create(Order order);

    /**
     * 创建订单 测试本地事务
     *
     * @param order order
     */
    void testTs(Order order);
}
