package com.jxy.repository;

import com.jxy.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: jxy
 * @Date: 2019/4/8 21:01
 * @Description:
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
