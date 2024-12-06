package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.dao.GoodsOrderDao;
import xyz.djma.domain.GoodsOrder;
import xyz.djma.service.GoodsOrderService;

import java.util.List;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Autowired
    private GoodsOrderDao goodsOrderDao;

    @Override
    public List<GoodsOrder> getAllByOrderId(Integer orderId) {
        return goodsOrderDao.getAllByOrderId(orderId);
    }
}
