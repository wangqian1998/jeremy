package com.jeremy.service;/**
 * @author wangqian on ${date} ${time}
 */

import com.jeremy.entity.User;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public interface UserService {

    List<User> findAll();

    User findByUserName(String username);
}
