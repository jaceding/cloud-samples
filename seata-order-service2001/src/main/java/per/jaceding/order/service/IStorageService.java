package per.jaceding.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import per.jaceding.payment.vo.Result;

/**
 * 库存业务层接口
 *
 * @author jaceding
 * @date 2020/6/22
 */
@FeignClient("seata-storage-service2002")
public interface IStorageService {

    /**
     * 库存数量加减
     *
     * @param productId 产品id
     * @param num       减少数量
     * @return 请求结果
     */
    @PostMapping("/storage")
    Result changeStorageNum(@RequestParam("productId") Long productId, @RequestParam("num") Integer num);
}
