package com.jxy.repository;

import com.jxy.entity.JxyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述
 *
 * @author ybl
 * @version 1.1
 * @since 1.0
 */
public interface JxyUserRepository extends JpaRepository<JxyUser, Long> {

    JxyUser findByUserName(String userName);
}
