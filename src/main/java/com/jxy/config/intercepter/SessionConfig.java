package com.jxy.config.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description
 * @author: jxy
 * @create: 2019-08-28 15:22
 */
@Configuration
public class SessionConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RedisSessionInterceptor interceptor() {
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有已api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        // 必须写成getSessionInterceptor()，否则SessionInterceptor中的@Autowired会无效
        registry.addInterceptor(interceptor()).addPathPatterns("/getAllUser").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }
}
