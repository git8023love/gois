package com.jeff.gois.core.service.impl;


import com.jeff.gois.core.bean.UserInfo;
import com.jeff.gois.core.dao.UserInfoMapper;
import com.jeff.gois.core.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findUserInfoByUserName(String userName) {
        return userInfoMapper.findUserInfoByUserName(userName);
    }
}
