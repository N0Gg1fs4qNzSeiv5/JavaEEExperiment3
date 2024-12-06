package xyz.djma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.djma.domain.Cart;
import xyz.djma.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Result save(@RequestBody Cart cart, HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        cart.setUserId(userId);

        boolean flag = cartService.save(cart);
        if (flag) {
            return new Result(Code.SAVE_OK, null, "添加成功");
        } else {
            return new Result(Code.SAVE_ERROR, null, "添加失败");
        }
    }

    @PutMapping
    public Result update(@RequestBody Cart cart, HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        cart.setUserId(userId);

        boolean flag = cartService.update(cart);
        if (flag) {
            return new Result(Code.UPDATE_OK, null, "修改成功");
        } else {
            return new Result(Code.UPDATE_ERROR, null, "修改失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id, HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");

        boolean flag = cartService.delete(id, userId);
        if (flag) {
            return new Result(Code.DELETE_OK, null, "删除成功");
        } else {
            return new Result(Code.DELETE_ERROR, null, "删除失败");
        }
    }

    @GetMapping
    public Result getAllByUserId(HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");

        List<Cart> cartList = cartService.getAllByUserId(userId);
        return new Result(Code.GET_OK, cartList);
    }
}
