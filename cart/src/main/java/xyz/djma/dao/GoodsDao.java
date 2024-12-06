package xyz.djma.dao;

import org.apache.ibatis.annotations.Select;
import xyz.djma.domain.Goods;

import java.util.List;

public interface GoodsDao {
    @Select("select id, name, price, img from goods where id = #{id}")
    Goods getById(Integer id);

    @Select("select id, name, price, img from goods")
    List<Goods> getAll();
}
