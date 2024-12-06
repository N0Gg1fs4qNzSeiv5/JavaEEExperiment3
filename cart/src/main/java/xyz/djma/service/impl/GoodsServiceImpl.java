package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.dao.GoodsDao;
import xyz.djma.domain.Goods;
import xyz.djma.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods getById(Integer id) {
        return goodsDao.getById(id);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDao.getAll();
    }
}
