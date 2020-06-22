package per.jaceding.payment.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import per.jaceding.payment.dao.PaymentMapper;
import per.jaceding.payment.entity.Payment;
import per.jaceding.payment.service.PaymentService;

/**
 * 支付业务层实现
 *
 * @author jaceding
 * @date 2020/6/16
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

}
