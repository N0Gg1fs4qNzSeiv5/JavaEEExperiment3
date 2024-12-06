package xyz.djma.dao;

import org.apache.ibatis.annotations.*;
import xyz.djma.domain.Cart;

import java.util.List;

public interface CartDao {
    @Insert("insert into cart (count, goods_id, user_id) values (#{count}, #{goodsId}, #{userId})")
    int save(Cart cart);

    @Update("update cart set count = #{count} where id = #{id}")
    int update(Cart cart);

    @Delete("delete from cart where id = #{id}")
    int delete(Integer id);

    @Delete("delete from cart where user_id = #{userId}")
    int deleteByUserId(Integer userId);

    @Select("select id, count, goods_id as goodsId, user_id as userId from cart where id = #{id}")
    Cart getById(Integer id);

    @Select("select id, count, goods_id as goodsId, user_id as userId from cart")
    List<Cart> getAll();

    @Select("select id, count, goods_id as goodsId, user_id as userId from cart where user_id = #{userId}")
    List<Cart> getAllByUserId(Integer userId);

    @Select("select id, count, goods_id as goodsId, user_id as userId from cart where user_id = #{userId} and goods_id = #{goodsId}")
    Cart getByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
}
