package per.jaceding.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.jaceding.payment.vo.Result;
import per.jaceding.storage.service.StorageService;

/**
 * 库存控制层
 *
 * @author jaceding
 * @date 2020/6/22
 */
@RequestMapping("/storage")
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping
    Result changeStorageNum(@RequestParam("productId") Long productId, @RequestParam("num") Integer num) {
        storageService.changeStorageNum(productId, num);
        return Result.success("修改成功");
    }
}
