package com.jeff.gois.core.controller;

import com.jeff.gois.common.Result;
import com.jeff.gois.common.util.ResultUtil;
import com.jeff.gois.core.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping("/findSysPermissionByUsername")
    public Result findSysPermissionByUsername(String username) {
        return ResultUtil.success(sysPermissionService.findSysPermissionByUsername(username));
    }
}
