package per.jaceding.order.service;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import per.jaceding.order.vo.PaymentVO;
import per.jaceding.payment.vo.Result;

/**
 * 支付业务层接口
 *
 * @author jaceding
 * @date 2020/6/18
 */
@FeignClient(value = "payment-service")
public interface PaymentService {

    /**
     * 获取支付信息
     * 这里方法名无所谓，但是GetMapping中的URL必须与服务提供方一直
     * 参数一定要绑定参数名
     * 若通过header来传递参数中文需转码 编码后在传递(URLEncoder.encode(xxx, "utf-8"))
     * provider解码(URLDecoder.decode(xxx, "utf-8"))
     *
     * @param id 主键
     */
    @GetMapping("/payment/{id}")
    Result getOne(@PathVariable("id") Long id);

    /**
     * 创建支付信息
     *
     * @param paymentVO 支付信息VO
     */
    @PostMapping(value = "/payment")
    Result create(PaymentVO paymentVO);
}
