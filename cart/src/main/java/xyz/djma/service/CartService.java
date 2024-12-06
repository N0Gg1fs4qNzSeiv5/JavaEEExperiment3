package xyz.djma.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.djma.domain.Cart;

import java.util.List;

@Transactional
public interface CartService {
    /**
     * 添加购物车信息
     * @param cart Cart对象
     * @return 是否添加成功
     */
    boolean save(Cart cart);

    /**
     * 修改购物车信息
     * @param cart Cart对象
     * @return 是否修改成功
     */
    boolean update(Cart cart);

    /**
     * 删除购物车信息
     * @param id Cart对象的id
     * @return 是否删除成功
     */
    boolean delete(Integer id, Integer userId);

    /**
     * 根据用户id获取所有购物车信息
     * @return 包含所有购物车信息的列表
     */
    List<Cart> getAllByUserId(Integer userId);

    /**
     * 根据用户id和商品id查询
     * @param userId 用户id
     * @param goodsId 商品id
     * @return Cart对象
     */
    Cart getByUserIdAndGoodsId(Integer userId, Integer goodsId);
}
