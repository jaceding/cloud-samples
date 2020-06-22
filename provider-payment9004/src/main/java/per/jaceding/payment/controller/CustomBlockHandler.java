package per.jaceding.payment.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import per.jaceding.payment.vo.Result;

/**
 * 自定义的规则降级处理
 *
 * @author jaceding
 * @date 2020/6/22
 */
@Slf4j
public class CustomBlockHandler {

    /**
     * 全局降级处理
     */
    public static Result globalHandler(BlockException e) {
        log.error("globalHandler", e);
        return Result.fail("globalHandler");
    }

    /**
     * 降级处理
     */
    public static Result handler1(BlockException e){
        log.error("handler1", e);
        return Result.fail("handler1");
    }
}
