package com.jeremy.service.impl;

import com.jeremy.dao.UserMapper;
import com.jeremy.entity.RoleMenu;
import com.jeremy.entity.UserRole;
import com.jeremy.service.RoleMenuService;
import com.jeremy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<RoleMenu> findAll() {
        return null;
    }
}
