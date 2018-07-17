package com.jeff.gois.core.controller;


import com.jeff.gois.core.bean.UserInfo;
import com.jeff.gois.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/findUserInfoByUserName")
    public UserInfo findUserInfoByUserName(String userName){
        return userInfoService.findUserInfoByUserName(userName);
    }
}
