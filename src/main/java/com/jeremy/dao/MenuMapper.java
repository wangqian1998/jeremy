package com.jeremy.dao;/**
 * @author wangqian on ${date} ${time}
 */

import com.jeremy.entity.Menu;
import com.jeremy.entity.User;

import java.util.List;

/**
 * @author chenjun on 2019/4/22
 */
public interface MenuMapper {
    List<Menu> findAll();

    List<Menu> findByRoleId(Integer roleId);
}
