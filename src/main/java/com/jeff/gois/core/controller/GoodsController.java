package com.jeff.gois.core.controller;

import com.jeff.gois.common.Result;
import com.jeff.gois.common.util.ResultUtil;
import com.jeff.gois.core.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 查询所有的商品列表
     * @return
     */
    @RequestMapping("findGoodsList")
    public Result findGoodsList(){
        return  ResultUtil.success(goodsService.findGoodsList());
    }

    /**
     * 清除缓存
     * @return
     */
    @RequestMapping("/deleteFromCache")
    public Result deleteFromCache(){
        goodsService.deleteFromCache();
        return ResultUtil.success();
    }

    /**
     * ehcache 注掉
     * @param id
     * @return
     */
    @RequestMapping("/findGoodsById")
    public Result findGoodsById(@RequestParam Integer id){
        return  ResultUtil.success(/*goodsService.findGoodsById(id)*/);
    }
}
