package com.jeff.gois.core.bean;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Mapper
@Component
public class SysPermission implements Serializable {
    /**
     * 资源Id
     */
    private Integer id;

    /**
     *
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 名称
     */
    private String name;

    /**
     * 父Id
     */
    private Long parentId;

    /**
     * 父Id列表
     */
    private String parentIds;

    /**
     * 权限字符串
     * menu例子：role:*
     * button例子：role:create,role:update,role:delete,role:view
     */
    private String permission;

    /**
     * 资源类型，[menu|button]
     */
    private String resourceType;

    /**
     * 资源路径
     */
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}