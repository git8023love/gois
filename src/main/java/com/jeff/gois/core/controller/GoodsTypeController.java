package com.jeff.gois.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeff.gois.common.Result;
import com.jeff.gois.common.util.ResultUtil;
import com.jeff.gois.core.bean.GoodsType;
import com.jeff.gois.core.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class GoodsTypeController {


    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 根据商品分类名称查询
     * @param name
     * @return
     */
    @RequestMapping("/findGoodsType")
    public Result findGoodsType(String name){
        List<GoodsType> goodsType0 = goodsTypeService.findGoodsTypeByName(name);
        return ResultUtil.success(goodsType0);
    }

    /**
     * 根据商品分类名称查询
     * @param name
     * @return
     */
    @RequestMapping("/findGoodsType1")
    public Result findGoodsType0(String name){
        List<GoodsType> goodsType0 = goodsTypeService.findGoodsTypeByName1(name);
        return ResultUtil.success(goodsType0);
    }
    /**
     * 根据商品分类名称查询
     * @param name
     * @return
     */
    @RequestMapping("/findGoodsType2")
    public Result findGoodsType2(String name){
        List<GoodsType> goodsType0 = goodsTypeService.findGoodsTypeByName2(name);
        return ResultUtil.success(goodsType0);
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/page")
    public Result page(){
        PageHelper.startPage(1,5);
        List<GoodsType> pageGoodsType = goodsTypeService.findAll();
        PageInfo pageInfo = new PageInfo(pageGoodsType);
        return ResultUtil.success(pageGoodsType);
    }
}
