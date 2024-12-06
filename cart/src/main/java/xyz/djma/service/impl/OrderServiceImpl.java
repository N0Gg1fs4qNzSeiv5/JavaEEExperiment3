package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.controller.Code;
import xyz.djma.dao.*;
import xyz.djma.domain.Cart;
import xyz.djma.domain.Goods;
import xyz.djma.domain.GoodsOrder;
import xyz.djma.domain.Order;
import xyz.djma.exception.BusinessException;
import xyz.djma.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsOrderDao goodsOrderDao;

    @Override
    public Double save(Order order) {
        // 验证用户
        Integer userId = order.getUserId();
        if (userId == null || userDao.getById(userId) == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "用户未登陆");
        }
        // 验证商品
        if (cartDao.getAllByUserId(userId).isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "购物车中没有商品");
        }

        // 验证收货地址
        String address = order.getAddress();
        if (address == null || address.isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "收货地址不能为空");
        }
        if (address.length() > 1000) {
            throw new BusinessException(Code.BUSINESS_ERR, "收获地址最多1000字符");
        }

        // 计算支付金额
        List<Cart> cartList = cartDao.getAllByUserId(userId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Cart cart : cartList) {
            Goods goods = goodsDao.getById(cart.getGoodsId());
            BigDecimal price = BigDecimal.valueOf(goods.getPrice());
            price = price.multiply(BigDecimal.valueOf(cart.getCount()));
            totalPrice = totalPrice.add(price);
        }
        order.setPay(totalPrice.doubleValue());

        // 添加
        order.setNo(UUID.randomUUID().toString());
        int rows = orderDao.save(order);
        if (rows > 0) {
            // 成功下单，将商品信息保存到goods_order中
            Integer orderId = order.getId();
            GoodsOrder goodsOrder = new GoodsOrder();
            cartList.forEach(cart -> {
                goodsOrder.setGoodsId(cart.getGoodsId());
                goodsOrder.setOrderId(orderId);
                goodsOrder.setCount(cart.getCount());
                goodsOrderDao.save(goodsOrder);
            });
            // 删除购物车信息
            cartDao.deleteByUserId(userId);
            return totalPrice.doubleValue();
        }
        return null;
    }

    @Override
    public List<Order> getAllByUserId(Integer userId) {
        return orderDao.getAllByUserId(userId);
    }
}
