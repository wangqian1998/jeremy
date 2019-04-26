package com.jeremy.security;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjun on 2019/4/22
 */
@Configuration
public class ShiroConfig {

    // 将自己定义的relam实例化
    @Bean
    public ShiroRelam shiroRelam() {
        ShiroRelam shiroRelam = new ShiroRelam();
        return shiroRelam;
    }

    /**
     * 权限管理
     * @return
     */
    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRelam());
        return defaultWebSecurityManager;
    }

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager());
        // 配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/admin/user/loginPage");
        // 登录成功页面
        shiroFilterFactoryBean.setSuccessUrl("/admin/user/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/user/loginPage");
        Map<String, String> filterDefaultMap = new HashMap<>();
        filterDefaultMap.put("/logout", "logout");
        filterDefaultMap.put("/admin/user/login", "anon");
        filterDefaultMap.put("/static/**", "anon");
      /*  filterDefaultMap.put("/**.js", "anon");
        filterDefaultMap.put("/**.css", "anon");*/
        filterDefaultMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterDefaultMap);
        return shiroFilterFactoryBean;
    }



    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(getSecurityManager());
        return  authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro声明周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}

