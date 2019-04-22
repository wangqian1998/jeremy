package com.jeremy.service.impl;

import com.jeremy.dao.UserMapper;
import com.jeremy.entity.User;
import com.jeremy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }


    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
