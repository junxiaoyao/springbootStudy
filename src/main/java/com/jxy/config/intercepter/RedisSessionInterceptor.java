package com.jxy.config.intercepter;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * @description
 * @author: jxy
 * @create: 2019-08-28 15:02
 */
public class RedisSessionInterceptor implements HandlerInterceptor {

    public final static String SESSION_USER_ID_KEY = "loginUserId";

    public final static String REDIS_USER_ID_KEY_PREFIX = "loginUser";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("i am RedisSessionInterceptor time :"+new Date().getTime());
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_USER_ID_KEY) != null) {
            try {
                String loginUserId = redisTemplate.opsForValue()
                    .get(REDIS_USER_ID_KEY_PREFIX + (long) session.getAttribute(SESSION_USER_ID_KEY));
                if (loginUserId != null && loginUserId.equals(session.getId())) {
                    return true;
                }else {
                    response.sendRedirect("/login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Object o, Exception e) throws Exception {
    }
}
