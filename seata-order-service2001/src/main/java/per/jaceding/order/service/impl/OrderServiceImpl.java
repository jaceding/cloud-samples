package per.jaceding.order.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import per.jaceding.order.dao.OrderMapper;
import per.jaceding.order.domain.Order;
import per.jaceding.order.service.IAccountService;
import per.jaceding.order.service.IStorageService;
import per.jaceding.order.service.OrderService;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 订单业务层实现
 *
 * @author jaceding
 * @date 2020/6/22
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IAccountService accountService;

    @GlobalTransactional(name = "jace-order-create", rollbackFor = Exception.class)
    @Override
    public void create(Order order) {
        log.info("创建订单 -- start");
        this.save(order);

        log.info("调用库存微服务-扣减-start");
        storageService.changeStorageNum(order.getProductId(), order.getCount());
        log.info("调用库存微服务-扣减-end");

        log.info("调用账户微服务-扣减-start");
        accountService.changeMoney(order.getUserId(), order.getMoney());
        log.info("调用账户微服务-扣减-end");

        log.info("修改订单状态 -> 已完成 -start");
        order.setStatus(1);
        this.updateById(order);
        log.info("修改订单状态 -> 已完成 -end");
        log.info("创建订单 -- end");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTs(Order order) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        int nextInt = threadLocalRandom.nextInt(100);
        log.info("nextInt#" + nextInt);
        if (nextInt > 60) {
            throw new IllegalArgumentException();
        }
        this.save(order);
    }
}
