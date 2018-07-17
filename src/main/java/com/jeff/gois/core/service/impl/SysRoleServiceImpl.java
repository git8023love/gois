package com.jeff.gois.core.service.impl;


import com.jeff.gois.core.bean.SysRole;
import com.jeff.gois.core.dao.SysRoleMapper;
import com.jeff.gois.core.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public SysRole findSysRoleByUid(Integer uid) {
        return sysRoleMapper.findSysRoleByUid(uid);
    }
}
