package per.jaceding.storage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import per.jaceding.storage.dao.StorageMapper;
import per.jaceding.storage.domain.Storage;
import per.jaceding.storage.service.StorageService;

/**
 * 库存业务层实现
 *
 * @author jaceding
 * @date 2020/6/22
 */
@Slf4j
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    @Override
    public void changeStorageNum(Long productId, Integer num) {
        log.info("changeStorageNum - start");
        log.info("productId#" + productId);
        log.info("num#" + num);
        QueryWrapper<Storage> queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_id", productId);
        Storage storage = this.getOne(queryWrapper);
        storage.setUsed(storage.getUsed() + num);
        storage.setResidue(storage.getResidue() - num);
        this.updateById(storage);
        log.info("changeStorageNum - end");
    }
}
