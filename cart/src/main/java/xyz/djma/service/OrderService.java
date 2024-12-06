package xyz.djma.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.djma.domain.Goods;
import xyz.djma.domain.Order;

import java.util.List;

@Transactional
public interface OrderService {
    /**
     * 保存订单信息
     * @param order Order对象
     * @return 付款金额
     */
    Double save(Order order);

    /**
     * 根据用户id获取所有Order信息
     * @param userId User.id
     * @return 包含Order的集合
     */
    List<Order> getAllByUserId(Integer userId);
}
