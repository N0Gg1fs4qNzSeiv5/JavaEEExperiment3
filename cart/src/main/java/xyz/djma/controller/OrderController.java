package xyz.djma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.djma.domain.Order;
import xyz.djma.service.OrderService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result save(@RequestBody Order order, HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");
        order.setUserId(userId);

        Double totalPay = orderService.save(order);
        if (totalPay != null) {
            return new Result(Code.SAVE_OK, totalPay, "下单成功");
        } else {
            return new Result(Code.SAVE_ERROR, null, "下单失败");
        }
    }

    @GetMapping
    public Result getAllByUserId(HttpSession session) {
        // 获取session中的用户信息
        Integer userId = (Integer) session.getAttribute("userId");

        List<Order> orderList = orderService.getAllByUserId(userId);
        return new Result(Code.GET_OK, orderList);
    }
}
