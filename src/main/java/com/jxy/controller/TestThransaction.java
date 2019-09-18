package com.jxy.controller;

import com.jxy.service.JxyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author: jxy
 * @create: 2019-09-18 10:25
 */
@Controller
@RequestMapping("tr")
public class TestThransaction {

    @Autowired
    JxyUserService userService;

    // jpa事务方式
    @RequestMapping("save")
    public void testSave() {
        userService.transAdd();
    }

    // jpa非事务方式
    @RequestMapping("save2")
    public void testSave2() {
        userService.transAdd2();
    }

    // jdbc事务方式
    @RequestMapping("save3")
    public void testSave3() {
        userService.addBySql();
    }

    // jdbc非事务方式
    @RequestMapping("save4")
    public void testSave4() {
        userService.addBySql2();
    }
}
