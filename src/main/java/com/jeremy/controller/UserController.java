package com.jeremy.controller;

import com.jeremy.entity.User;
import com.jeremy.service.UserService;
import com.jeremy.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("这是测试的方法a");
        return null;
    }

    /**
     * 登陆的方法
     * @param username
     * @param password
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Map<String, Object> login(String username, String password, HttpSession session) {

        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isEmpty(username)) {
            map.put("success", false);
            map.put("info", "用户名不能为空");
        }

        if (StringUtil.isEmpty(password)) {
            map.put("success", false);
            map.put("info", "密码不能为空");
        }
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            String name = (String) SecurityUtils.getSubject().getPrincipal();
            User user = userService.findByUserName(name);
            session.setAttribute("user", user);
            map.put("success", true);
            map.put("info", "登陆成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("info", "用户名和密码错误");
        }

        return map;
    }

    /**
     * 跳转到主页的方法
     * @return
     */
    @RequestMapping("/main")
    public String main() {
        return "/user/main";
    }
}
