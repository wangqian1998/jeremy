package com.jeremy.entity;

/**
 * @author chenjun on 2019/4/22
 */
public class RoleMenu {
    private Integer id;
    private Integer roleId;
    private Integer MenuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return MenuId;
    }

    public void setMenuId(Integer menuId) {
        MenuId = menuId;
    }
}
