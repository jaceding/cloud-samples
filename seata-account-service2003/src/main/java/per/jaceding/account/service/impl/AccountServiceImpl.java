package per.jaceding.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import per.jaceding.account.domain.Account;
import per.jaceding.account.dao.AccountMapper;
import per.jaceding.account.service.AccountService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 账户控制层实现
 *
 * @author jaceding
 * @date 2020/6/22
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public void changeMoney(Long userId, BigDecimal money) {
        log.info("changeMoney - start");
        log.info("userId#" + userId);
        log.info("money#" + money);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QueryWrapper<Account> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        Account account = this.getOne(queryWrapper);
        account.setUsed(account.getUsed().add(money));
        account.setResidue(account.getResidue().subtract(money));
        this.updateById(account);
        log.info("changeMoney - end");
    }
}
