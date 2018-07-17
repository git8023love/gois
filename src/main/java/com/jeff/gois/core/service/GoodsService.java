package com.jeff.gois.core.service;


import com.jeff.gois.core.bean.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findGoodsList();

    void deleteFromCache();

    //Goods findGoodsById(Integer id);
}
