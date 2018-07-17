package com.jeff.gois.core.service.impl;


import com.jeff.gois.common.dynamic.TargetDataSource;
import com.jeff.gois.core.bean.GoodsType;
import com.jeff.gois.core.dao.GoodsTypeMapper;
import com.jeff.gois.core.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findGoodsTypeByName(String name) {
        return goodsTypeMapper.findGoodsTypeByName(name);
    }

    @Override
    public List<GoodsType> findAll() {
        return goodsTypeMapper.findAll();
    }

    @TargetDataSource("ds1")
    @Override
    public List<GoodsType> findGoodsTypeByName1(String name) {
        return goodsTypeMapper.findGoodsTypeByName(name);
    }
     @TargetDataSource("ds2")
    @Override
    public List<GoodsType> findGoodsTypeByName2(String name) {
        return goodsTypeMapper.findGoodsTypeByName(name);
    }
}
