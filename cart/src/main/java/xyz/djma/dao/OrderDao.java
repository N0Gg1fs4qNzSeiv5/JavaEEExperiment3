package xyz.djma.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import xyz.djma.domain.Order;

import java.util.List;

public interface OrderDao {
    @Insert("insert into `order` (no, pay, address, user_id) values (#{no}, #{pay}, #{address}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Order order);

    @Select("select id, no, create_at as createAt, pay, address, user_id as userId from `order` where user_id = #{uderId} order by create_at desc")
    List<Order> getAllByUserId(Integer userId);
}
