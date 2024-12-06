package xyz.djma.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.djma.domain.Goods;

import java.util.List;

@Transactional
public interface GoodsService {
    /**
     * 根据id获取商品信息
     * @param id 商品id
     * @return Goods对象
     */
    Goods getById(Integer id);

    /**
     * 获取所有商品信息
     * @return 包含所有商品的集合
     */
    List<Goods> getAll();
}
