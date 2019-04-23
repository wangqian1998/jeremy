package com.jeremy.controller;

import com.jeremy.entity.User;
import com.jeremy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("/index")
    public List<User> index() {
        System.out.println("进来了");
        return userService.findAll();
    }

    @RequestMapping("/loginPage")
    public String show() {
        System.out.println("进来了跳转页面的方法");
        return "/user/index";
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println("这是测试的方法");
        return null;
    }
}
