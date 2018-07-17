package com.jeff.gois.core.dao;

import com.jeff.gois.core.bean.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public  interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    SysPermission findSysPermissionByRoleId(Integer roleId);

    List<SysPermission> findSysPermissionByUsername(String username);

    List<SysPermission> findAllSysPermission();
}