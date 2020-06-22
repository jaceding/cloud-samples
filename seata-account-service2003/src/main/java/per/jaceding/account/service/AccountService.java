package per.jaceding.account.service;

import per.jaceding.account.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * 账户业务层实现
 *
 * @author jaceding
 * @date 2020/6/22
 */
public interface AccountService extends IService<Account> {

    /**
     * 修改余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void changeMoney(Long userId, BigDecimal money);
}
