package com.jeff.gois.common.shiro;

import com.jeff.gois.core.bean.SysPermission;
import com.jeff.gois.core.bean.UserInfo;
import com.jeff.gois.core.service.SysPermissionService;
import com.jeff.gois.core.service.SysRoleService;
import com.jeff.gois.core.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    UserInfoService userInfoService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();//获取用户信息
        logger.info("username={}",userInfo.getUsername());

        List<SysPermission> sysPermissionsList = sysPermissionService.findSysPermissionByUsername(userInfo.getUsername()); //获取用户资源
        if (sysPermissionsList != null && !sysPermissionsList.isEmpty()) {
            for (SysPermission sysPermission : sysPermissionsList) {
                // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
                simpleAuthorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        UserInfo userInfo = userInfoService.findUserInfoByUserName(username);
        if(userInfo == null) throw new UnknownAccountException();
        if(2 == userInfo.getState()) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getCredentialsSalt()), getName());

        return simpleAuthenticationInfo;
    }
}
