package com.jxy.controller;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.jxy.entity.Role;
import com.jxy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 11:07
 * @Description:
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RoleRepository roleRepository;
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        Role role=new Role();
        role.setUserName("pp");
        role.setRemark("sadasd");
        role.setCreateTime(Calendar.getInstance().getTime());
        role.setUserId(2l);
        role.setRoleName("测试角色");
        roleRepository.save(role);
        Map<String, Object> user = new HashMap<>();
        user.put("userName", "jxy");
        model.addAttribute("user", user);
        return "homePage";
    }

}
