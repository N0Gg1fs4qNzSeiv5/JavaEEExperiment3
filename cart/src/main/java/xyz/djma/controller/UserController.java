package xyz.djma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.djma.domain.User;
import xyz.djma.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        boolean flag = userService.register(user);
        if (flag) {
            return new Result(Code.SAVE_OK, null, "注册成功");
        } else {
            return new Result(Code.SAVE_ERROR, null, "注册失败");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        boolean flag = userService.login(user);
        if (flag) {
            // 设置session
            User tmpUser = userService.getUserByUsername(user.getUsername());
            session.setAttribute("userId", tmpUser.getId());
            return new Result(Code.GET_OK, null, "登陆成功");
        } else {
            return new Result(Code.GET_ERROR, null, "登陆失败");
        }
    }
}
