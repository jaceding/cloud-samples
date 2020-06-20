package per.jaceding.payment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import per.jaceding.payment.entity.Payment;

/**
 * 订单持久层
 *
 * @author  jaceding
 * @date    2020/6/16
 */
@CacheNamespace
public interface PaymentMapper extends BaseMapper<Payment> {
}