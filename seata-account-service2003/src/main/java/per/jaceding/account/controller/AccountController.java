package per.jaceding.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.account.service.AccountService;
import per.jaceding.payment.vo.Result;

import java.math.BigDecimal;

/**
 * 账户控制层
 *
 * @author jaceding
 * @date 2020/6/22
 */
@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/money")
    public Result changeMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.changeMoney(userId, money);
        return Result.success("修改余额成功");
    }
}
