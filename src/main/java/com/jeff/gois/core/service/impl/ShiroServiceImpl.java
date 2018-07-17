package com.jeff.gois.core.service.impl;

import com.jeff.gois.core.bean.SysPermission;
import com.jeff.gois.core.service.ShiroService;
import com.jeff.gois.core.service.SysPermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService{

    private static Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class);

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    SysPermissionService sysPermissionService;

    /**
     * 初始化权限
     * @return
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        //权限控制,从数据库取值
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/jquery/**", "anon");
        filterChainDefinitionMap.put("/jquery-slimscroll/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/linearicons/**", "anon");
        List<SysPermission> sysPermissionList = sysPermissionService.findAllSysPermission();
        for (SysPermission sysPermission : sysPermissionList) {
            if(!StringUtils.isEmpty(sysPermission.getUrl())) {
                String permission = "perms[" + sysPermission.getUrl()+ "]";
                filterChainDefinitionMap.put(sysPermission.getUrl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        synchronized(shiroFilterFactoryBean){
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            //清空旧权限
            filterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            //设置新权限
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                filterChainManager.createChain(url, chainDefinition);
            }
            logger.info(">>>>>>>>>>>>>>>>> update shiro filterChainManager success <<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

}
