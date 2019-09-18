package com.jxy.service;

import com.jxy.entity.JxyUser;
import com.jxy.repository.JxyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description
 * @author: jxy
 * @create: 2019-09-18 10:19
 */
@Service
public class JxyUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JxyUserRepository repository;

    @Transactional
    public void transAdd() {
        addUSer();
    }

    public void transAdd2() {
        addUSer();
    }

    public void addUSer() {
        JxyUser u1 = new JxyUser();
        u1.setUserName("u1");
        u1.setUserRole("u1");
        u1.setUserPass("u1");
        JxyUser u2 = new JxyUser();
        u2.setUserName("u2");
        u2.setUserRole("u2");
        u2.setUserPass("u2");
        repository.save(u1);
        int i = 1 / 0;
        repository.save(u2);
    }

    @Transactional
    public void addBySql() {
        addBySqlImp();
    }

    public void addBySql2() {
        addBySqlImp();
    }

    public void addBySqlImp() {
        String u1 = "u1";
        String u2 = "u2";
        String sql = "INSERT INTO huser(user_name,user_pass,user_role) VALUES('";
        StringBuffer sql1 = new StringBuffer(sql);
        StringBuffer sql2 = new StringBuffer(sql);
        sql1.append(u1 + "','" + u1 + "','" + u1 + "')");
        sql2.append(u2 + "','" + u2 + "','" + u2 + "')");
        jdbcTemplate.execute(sql1.toString());
        int i = 1 / 0;
        jdbcTemplate.execute(sql2.toString());
    }
}
