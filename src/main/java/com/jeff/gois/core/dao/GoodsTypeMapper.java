package com.jeff.gois.core.dao;

import com.jeff.gois.core.bean.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);

    List<GoodsType> findGoodsTypeByName(String name);

    List<GoodsType> findAll();
}