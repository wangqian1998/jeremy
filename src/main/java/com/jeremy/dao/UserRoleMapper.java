package com.jeremy.dao;/**
 * @author wangqian on ${date} ${time}
 */

import com.jeremy.entity.Role;
import com.jeremy.entity.UserRole;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public interface UserRoleMapper {
    List<UserRole> findAll();
}
