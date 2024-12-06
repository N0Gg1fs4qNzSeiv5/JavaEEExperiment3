package xyz.djma.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import xyz.djma.domain.GoodsOrder;

import java.util.List;

public interface GoodsOrderDao {
    @Insert("insert into goods_order (goods_id, order_id, count) values (#{goodsId}, #{orderId}, #{count})")
    int save(GoodsOrder goodsOrder);

    @Select("select id, goods_id as goodsId, order_id as orderId, count from goods_order where order_id = #{orderId}")
    List<GoodsOrder> getAllByOrderId(Integer orderId);
}
