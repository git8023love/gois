package com.jeff.gois.core.service;


import com.jeff.gois.core.bean.UserInfo;

public interface UserInfoService {

    UserInfo findUserInfoByUserName(String userName);
}
