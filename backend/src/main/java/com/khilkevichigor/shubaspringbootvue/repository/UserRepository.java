package com.khilkevichigor.shubaspringbootvue.repository;

import com.khilkevichigor.shubaspringbootvue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username); //достать юзера по username
}
