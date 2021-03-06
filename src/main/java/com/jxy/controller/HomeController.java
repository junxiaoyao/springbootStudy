package com.jxy.controller;

import com.jxy.config.intercepter.RedisSessionInterceptor;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxy.entity.JxyUser;
import com.jxy.entity.Role;
import com.jxy.repository.JxyUserRepository;
import com.jxy.repository.RoleRepository;
import com.jxy.security.UserDetail;
import com.jxy.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
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

    private int size = 50;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    // @Autowired
    // private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        Authentication jxyUser = SecurityContextHolder.getContext().getAuthentication();
        JxyUser userDetails = userRepository.findByUserName(jxyUser.getName());
        HttpSession session = request.getSession();
        session.setAttribute(RedisSessionInterceptor.SESSION_USER_ID_KEY, userDetails.getId());
        redisTemplate.opsForValue().set(RedisSessionInterceptor.REDIS_USER_ID_KEY_PREFIX + userDetails.getId(), session.getId());
        /* Role role=new Role();
        role.setUserName("pp");
        role.setRemark("sadasd");
        role.setCreateTime(Calendar.getInstance().getTime());
        role.setUserId(2l);
        role.setRoleName("测试角色");
        roleRepository.save(role);*/

        Map<String, Object> user = new HashMap<>();
        user.put("userName", jxyUser.getName());
        model.addAttribute("user", user);
        return "homePage";
    }

    @RequestMapping("getUserById")
    @ResponseBody
    public JxyUser getUserById(long id) {
        long tB = System.currentTimeMillis();
        JxyUser user = userRepository.findOne(id);
        long tE = System.currentTimeMillis();
        System.out.println("time1:" + (tE - tB));
        long tB1 = System.currentTimeMillis();
        // jdbcTemplate.execute("select * from huser t where t.id='" + id + "'");
        long tE1 = System.currentTimeMillis();
        System.out.println("time2:" + (tE1 - tB1));
        return user;
    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public Page<JxyUser> getAllUser(int page, int pageSize) {
        // PageHelper.startPage(page, pageSize);
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

    @RequestMapping("getSize")
    @ResponseBody
    public int loginGet() {
        return size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
