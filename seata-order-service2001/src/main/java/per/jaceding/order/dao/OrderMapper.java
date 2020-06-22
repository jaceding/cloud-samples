package per.jaceding.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import per.jaceding.order.domain.Order;

/**
 * 订单持久层
 *
 * @author  jaceding
 * @date    2020/6/22
 */
@CacheNamespace
public interface OrderMapper extends BaseMapper<Order> {
}