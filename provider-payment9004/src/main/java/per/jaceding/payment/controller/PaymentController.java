package per.jaceding.payment.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import per.jaceding.payment.entity.Payment;
import per.jaceding.payment.service.PaymentService;
import per.jaceding.payment.vo.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 支付 控制层
 *
 * @author jaceding
 * @date 2020/6/16
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 创建支付信息
     */
    @PostMapping
    public Result create(@RequestBody Payment payment) {
        try {
            if (paymentService.save(payment)) {
                return Result.success();
            }
            log.info("创建成功");
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return Result.fail("创建失败");
    }

    /**
     * 查询支付信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public Result getOne(@PathVariable("id") Long id) {
        try {
            Payment payment = paymentService.getById(id);
            if (payment == null) {
                return Result.fail("查询失败，id:" + id);
            }
            return Result.success(payment);
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }

    /**
     * 获取服务信息
     */
    @GetMapping("/discovery")
    public Result getDiscovery() {
        try {
            Map<String, Object> dataMap = new HashMap<>(4, 1);
            List<String> services = discoveryClient.getServices();
            dataMap.put("services", services);
            List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
            dataMap.put("instances", instances);
            dataMap.put("discoveryClient", discoveryClient);
            return Result.success(dataMap);
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }

    /**
     * 测试OpenFeign超时和日志打印
     */
    @GetMapping("/openFeign")
    public Result openFeign(){
        try{
            log.info("sleep 2 s");
            TimeUnit.SECONDS.sleep(2);
            return Result.success("openFeign");
        } catch (Exception e) {
            log.error("查询失败", e);
        }
        return Result.fail("查询失败");
    }

    /**
     * 测试Sentinel热点key限流
     * SentinelResource value 需唯一
     *
     * @param id id
     * @param name name
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "hotKey", blockHandler = "testHotKeyHandler")
    public Result testHotKey(@RequestParam(value = "id", required = false) Integer id,
                             @RequestParam(value = "name", required = false) String name){
        log.info("id#" + id);
        log.info("name#" + name);
        return Result.success("Success");
    }

    public Result testHotKeyHandler(Integer id, String name, BlockException e){
        return Result.fail("testHotKeyHandler");
    }
}
