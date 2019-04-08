package com.jxy.controller;
import java.util.HashMap;
import java.util.Map;
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
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        Map<String, Object> user = new HashMap<>();
        user.put("userName", "jxy");
        model.addAttribute("user", user);
        return "homePage";
    }

}
