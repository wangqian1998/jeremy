package com.jeremy.service.impl;

import com.jeremy.dao.RoleMapper;
import com.jeremy.dao.UserMapper;
import com.jeremy.entity.Role;
import com.jeremy.entity.User;
import com.jeremy.service.RoleService;
import com.jeremy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    public List<Role> findAll() {
        return null;
    }


    public List<Role> findRoleByUserId(Integer userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
