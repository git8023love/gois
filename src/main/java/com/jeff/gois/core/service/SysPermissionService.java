package com.jeff.gois.core.service;


import com.jeff.gois.core.bean.SysPermission;

import java.util.List;

public interface SysPermissionService {

    SysPermission findSysPermissionByRoleId(Integer roleId);

    List<SysPermission> findSysPermissionByUsername(String username);

    List<SysPermission> findAllSysPermission();
}
