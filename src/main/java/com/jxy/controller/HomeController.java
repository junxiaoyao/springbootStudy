package com.jxy.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxy.entity.JxyUser;
import com.jxy.entity.Role;
import com.jxy.repository.JxyUserRepository;
import com.jxy.repository.RoleRepository;
import com.jxy.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @Autowired
    private JxyUserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
       /* Role role=new Role();
        role.setUserName("pp");
        role.setRemark("sadasd");
        role.setCreateTime(Calendar.getInstance().getTime());
        role.setUserId(2l);
        role.setRoleName("测试角色");
        roleRepository.save(role);*/
        Authentication jxyUser = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> user = new HashMap<>();
        user.put("userName", jxyUser.getName());
        model.addAttribute("user", user);
        return "homePage";
    }

    @RequestMapping("getUserById")
    @ResponseBody
    public JxyUser getUserById(long id) {
        JxyUser user = userRepository.findOne(id);
        return user;
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public Page<JxyUser> getAllUser(int page, int pageSize) {
        //  PageHelper.startPage(page, pageSize);
        PageRequest pageRequest = new PageRequest(page, pageSize);
      /*  List<JxyUser> users = userRepository.findAll();
        PageInfo<JxyUser> info = new PageInfo<>(users);*/
        Page<JxyUser> jxyUsers = userRepository.findAll(pageRequest);
        return jxyUsers;
    }

    @RequestMapping("login")
    public String loginGet(Model model, String error) {
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        return "login";
    }

    @RequestMapping(value = "regist", method = RequestMethod.GET)
    public String regist(Model model) {
        JxyUser user = new JxyUser();
        user.setUserName("sdsd");
        user.setUserRole("注册用户");
        model.addAttribute("user", user);
        return "regist";
    }

    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public String registPost(JxyUser user) {
        userRepository.save(user);
        return "redirect:/login";
    }
}
