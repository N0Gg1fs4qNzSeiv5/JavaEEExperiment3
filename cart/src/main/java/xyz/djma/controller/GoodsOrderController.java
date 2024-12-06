package xyz.djma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.djma.domain.GoodsOrder;
import xyz.djma.service.GoodsOrderService;

import java.util.List;

@RestController
@RequestMapping("/goods_orders")
public class GoodsOrderController {
    @Autowired
    private GoodsOrderService goodsOrderService;

    @GetMapping("/{orderId}")
    public Result getAllByOrderId(@PathVariable Integer orderId) {
        List<GoodsOrder> goodsOrderList = goodsOrderService.getAllByOrderId(orderId);
        return new Result(Code.GET_OK, goodsOrderList);
    }
}
