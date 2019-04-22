package com.jeremy.service;/**
 * @author wangqian on ${date} ${time}
 */

import com.jeremy.entity.Role;
import com.jeremy.entity.User;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public interface RoleService {

     List<Role> findAll();

     List<Role> findRoleByUserId(Integer userId);
}
