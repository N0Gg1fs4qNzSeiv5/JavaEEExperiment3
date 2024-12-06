package xyz.djma.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.djma.domain.GoodsOrder;

import java.util.List;

@Transactional
public interface GoodsOrderService {
    /**
     * 根据订单id获取商品订单信息
     * @param orderId 订单id
     * @return 集合
     */
    List<GoodsOrder> getAllByOrderId(Integer orderId);
}
