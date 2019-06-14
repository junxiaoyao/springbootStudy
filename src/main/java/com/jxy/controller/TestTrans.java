package com.jxy.controller;

import com.jxy.entity.Role;
import com.jxy.repository.RoleRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: jxy
 * @create: 2019-06-14 09:42
 */
@RestController
@RequestMapping("test")
public class TestTrans {

  @Autowired
  private RoleRepository roleRepository;
  @RequestMapping("add")
  @Transactional
  public String testAdd(){
    Role role1=new Role();
    role1.setUserName("pp");
    role1.setRemark("sadasd");
    role1.setCreateTime(Calendar.getInstance().getTime());
    role1.setUserId(2l);
    role1.setRoleName("测试角色");
    roleRepository.save(role1);
    int i=5/0;
    Role role2=new Role();
    role2.setUserName("pp");
    role2.setRemark("sadasd");
    role2.setCreateTime(Calendar.getInstance().getTime());
    role2.setUserId(2l);
    role2.setRoleName("测试角色2");
    roleRepository.save(role2);
    return "sss";
  }
}
