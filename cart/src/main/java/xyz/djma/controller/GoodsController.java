package xyz.djma.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.djma.domain.Goods;
import xyz.djma.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        return new Result(Code.GET_OK, goods);
    }

    @GetMapping
    public Result getAll() {
        List<Goods> goodsList = goodsService.getAll();
        return new Result(Code.GET_OK, goodsList);
    }
}
