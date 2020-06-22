package per.jaceding.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 库存业务层接口
 *
 * @author jaceding
 * @date 2020/6/22
 */
@FeignClient(value = "seata-account-service2003")
public interface IAccountService {

    /**
     * 改变余额
     *
     * @param userId 用户id
     * @param money  金额数量
     */
    @PostMapping("/account/money")
    void changeMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
