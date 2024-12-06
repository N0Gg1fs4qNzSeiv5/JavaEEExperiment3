package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.controller.Code;
import xyz.djma.dao.UserDao;
import xyz.djma.domain.User;
import xyz.djma.exception.BusinessException;
import xyz.djma.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean register(User user) {
        // 参数校验
        checkUserInfo(user);

        // 判断重复用户
        User dbUser = userDao.getByUsername(user.getUsername());
        if (dbUser != null) {
            throw new BusinessException(Code.BUSINESS_ERR, "用户名已存在");
        }

        // 保存用户信息
        int rows = userDao.save(user);
        return rows > 0;
    }

    @Override
    public boolean login(User user) {
        // 参数校验
        checkUserInfo(user);

        // 查找用户并验证密码
        User dbUser = userDao.getByUsername(user.getUsername());
        if (dbUser == null) {
            throw new BusinessException(Code.BUSINESS_ERR, "用户不存在");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new BusinessException(Code.BUSINESS_ERR, "密码不正确");
        }

        // 登陆成功
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getById(id);
    }


    /**
     * 验证User对象中的username和password，参数有问题就抛出异常
     * @param user User对象
     */
    private void checkUserInfo(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        // 用户名校验
        if (username == null || username.isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "用户名不能为空");
        }
        if (username.length() > 16) {
            throw  new BusinessException(Code.BUSINESS_ERR, "用户名最多16位字符");
        }
        // 密码校验
        if (password == null || password.isEmpty()) {
            throw new BusinessException(Code.BUSINESS_ERR, "密码不能为空");
        }
        String regex = "^[0-9a-zA-Z]+$";
        if (!password.matches(regex)) {
            throw new BusinessException(Code.BUSINESS_ERR, "密码只能由字母和数字组成");
        }
        if (password.length() < 6 || password.length() > 16) {
            throw new BusinessException(Code.BUSINESS_ERR, "密码最少6位字符，密码最多16位字符");
        }
    }
}
