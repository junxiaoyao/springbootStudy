package com.jxy.config.securityConfig;

import com.jxy.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Auther: jxy
 * @Date: 2019/4/13 19:57
 * @Description:
 */
@Configuration
//@EnableWebSecurity
@ComponentScan("com.jxy.security")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityService securityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(securityService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
       // httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity.formLogin()
                //　//这里程序登陆页面，允许所有人进行登陆
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                //失败重新登录
                .failureUrl("/login?error=true")
                .and().logout().permitAll()
                //成功进入主界面
                // .successForwardUrl("/")
                .and().authorizeRequests()
                .antMatchers("/").hasRole("ADMIN")
                .antMatchers("/getUserById").permitAll()
                .antMatchers("/handMessage").permitAll()
                .antMatchers("/getAllUser").permitAll()
                .antMatchers("/getSize").permitAll();

    }
}
