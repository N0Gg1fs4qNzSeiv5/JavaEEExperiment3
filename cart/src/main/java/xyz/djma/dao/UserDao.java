package xyz.djma.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import xyz.djma.domain.User;

public interface UserDao {
    @Insert("insert into user (username, password) values (#{username}, #{password})")
    int save(User user);

    @Select("select id, username, password from user where username = #{username}")
    User getByUsername(String username);

    @Select("select id, username, password from user where id = #{id}")
    User getById(Integer id);
}
