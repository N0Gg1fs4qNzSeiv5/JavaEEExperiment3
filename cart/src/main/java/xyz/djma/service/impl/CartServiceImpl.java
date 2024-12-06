package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.controller.Code;
import xyz.djma.dao.CartDao;
import xyz.djma.dao.GoodsDao;
import xyz.djma.dao.UserDao;
import xyz.djma.domain.Cart;
import xyz.djma.exception.BusinessException;
import xyz.djma.exception.SystemException;
import xyz.djma.service.CartService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(Cart cart) {
        checkCartInfo(cart);

        // 判断商品是否存在，存在就删除
        Cart dbCart = cartDao.getByUserIdAndGoodsId(cart.getUserId(), cart.getGoodsId());
        if (dbCart != null) {
            cartDao.delete(dbCart.getId());
        }

        // 添加商品信息
        int rows = cartDao.save(cart);
        return rows > 0;
    }

    @Override
    public boolean update(Cart cart) {
        // 判断购物车信息是否存在
        Integer id = cart.getId();
        Cart dbCart = null;
        if (id == null || (dbCart = cartDao.getById(id)) == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "购物车信息不存在");
        }

        if (cart.getCount() <= 0) {
            throw new BusinessException(Code.BUSINESS_ERR, "商品数量不能小于1");
        }

        // 修改信息
        int rows = cartDao.update(cart);
        return rows > 0;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        Cart dbCart = cartDao.getById(id);
        if (dbCart == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "不存在要删除的信息");
        }

        if (!dbCart.getUserId().equals(userId)) {
            throw new BusinessException(Code.BUSINESS_ERR, "不能删除其他人的购物车信息");
        }

        int rows = cartDao.delete(id);
        return rows > 0;
    }

    @Override
    public List<Cart> getAllByUserId(Integer userId) {
        return cartDao.getAllByUserId(userId);
    }

    @Override
    public Cart getByUserIdAndGoodsId(Integer userId, Integer goodsId) {
        return cartDao.getByUserIdAndGoodsId(userId, goodsId);
    }

    /**
     * 验证Cart对象中的参数
     * @param cart Cart对象
     */
    private void checkCartInfo(Cart cart) {
        // 判断用户是否登陆
//        Integer userId = cart.getUserId();
//        if (userId == null || userDao.getById(userId) == null) {
//            throw new BusinessException(Code.BUSINESS_ERR, "用户未登陆");
//        }

        // 判断商品是否存在
        Integer goodsId = cart.getGoodsId();
        if (goodsId == null || goodsDao.getById(goodsId) == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "商品不存在");
        }

        // 判断商品数量
        Integer count = cart.getCount();
        if (count == null || count < 1) {
            throw new BusinessException(Code.BUSINESS_ERR, "商品数量不能小于1");
        }
    }
}
