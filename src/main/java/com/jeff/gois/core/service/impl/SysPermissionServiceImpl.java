package com.jeff.gois.core.service.impl;


import com.jeff.gois.core.bean.SysPermission;
import com.jeff.gois.core.dao.SysPermissionMapper;
import com.jeff.gois.core.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;
    @Override
    public SysPermission findSysPermissionByRoleId(Integer roleId) {
        return sysPermissionMapper.findSysPermissionByRoleId(roleId);
    }

    @Override
    public List<SysPermission> findSysPermissionByUsername(String username) {
        return sysPermissionMapper.findSysPermissionByUsername(username);
    }

    @Override
    public List<SysPermission> findAllSysPermission() {
        return sysPermissionMapper.findAllSysPermission();
    }
}
