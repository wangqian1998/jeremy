package com.jeremy.security;

import com.jeremy.entity.Menu;
import com.jeremy.entity.Role;
import com.jeremy.entity.User;
import com.jeremy.service.MenuService;
import com.jeremy.service.RoleService;
import com.jeremy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public class ShiroRelam extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String name = (String) SecurityUtils.getSubject().getPrincipal();
        // 通过用户名获取user实体对象
        User user = userService.findByUserName(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        for (Role role : roleList) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            List<Menu> menuList = menuService.findByRoleId(role.getId());
            // 添加菜单
            for (Menu menu : menuList) {
                simpleAuthorizationInfo.addStringPermission(menu.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 获取认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 进行身份认证
        String name = (String) authenticationToken.getPrincipal();
        User user = userService.findByUserName(name);
        if (user != null) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), name);
            return simpleAuthenticationInfo;
        } else {
            return null;
        }

    }
}
