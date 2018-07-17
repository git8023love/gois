package com.jeff.gois.core.service;



import com.jeff.gois.core.bean.GoodsType;

import java.util.List;

public interface GoodsTypeService {

    List<GoodsType> findGoodsTypeByName(String name);

    List<GoodsType> findAll();

    List<GoodsType> findGoodsTypeByName1(String name);

    List<GoodsType> findGoodsTypeByName2(String name);
}
