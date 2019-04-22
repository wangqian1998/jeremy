package com.jeremy.service.impl;

import com.jeremy.dao.UserMapper;
import com.jeremy.entity.Menu;
import com.jeremy.entity.User;
import com.jeremy.service.MenuService;
import com.jeremy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Menu> findAll() {
        return null;
    }
}
