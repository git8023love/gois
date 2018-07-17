package com.jeff.gois.core.service.impl;


import com.jeff.gois.core.bean.Goods;
import com.jeff.gois.core.bean.SysPermission;
import com.jeff.gois.core.dao.GoodsMapper;
import com.jeff.gois.core.service.GoodsService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 单引号不能少，否则会报错，被识别是一个对象;
     */
    public static final String CACHE_KEY = "'goods_Info'";

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "goods";

    @Cacheable(value="goodsInfo") //缓存,这里没有指定key.
    @Override
    public List<Goods> findGoodsList() {
        System.out.println("第一次查询");
        return goodsMapper.findGoodsList();
    }

    @CacheEvict(value="goodsInfo")
    @Override
    public void deleteFromCache() {
        System.out.println("删除");
    }

    /*@Cacheable(value=DEMO_CACHE_NAME,key="'goods_Info'+#id")
    @Override
    public Goods findGoodsById(Integer id) {
        System.err.println("没有走缓存！"+id);
        return goodsMapper.selectByPrimaryKey(id);
    }*/
}
