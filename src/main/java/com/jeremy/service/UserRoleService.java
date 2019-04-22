package com.jeremy.service;/**
 * @author wangqian on ${date} ${time}
 */

import com.jeremy.entity.User;
import com.jeremy.entity.UserRole;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public interface UserRoleService {

     List<UserRole> findAll();
}
