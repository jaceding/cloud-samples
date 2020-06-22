package per.jaceding.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import per.jaceding.storage.domain.Storage;

/**
 * 库存业务层接口
 *
 * @author jaceding
 * @date 2020/6/22
 */
public interface StorageService extends IService<Storage> {

    /**
     * 修改库存数量
     *
     * @param productId 产品id
     * @param num 数量
     */
    void changeStorageNum(Long productId, Integer num);
}
