package xyz.djma.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.djma.domain.User;

@Transactional
public interface UserService {
    /**
     * 注册
     * @param user controller层封装好的User对象
     * @return 注册是否成功
     */
    boolean register(User user);

    /**
     * 登陆
     * @param user controller层封装好的User对象
     * @return 登陆是否成功
     */
    boolean login(User user);

    /**
     * 通过用户名获取用户信息
     * @param username 用户名
     * @return User对象
     */
    User getUserByUsername(String username);

    /**
     * 通过id获取用户
     * @param id 用户id
     * @return User对象
     */
    User getUserById(Integer id);
}
