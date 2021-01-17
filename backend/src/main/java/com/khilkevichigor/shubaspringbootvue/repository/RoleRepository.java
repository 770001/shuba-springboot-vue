package com.khilkevichigor.shubaspringbootvue.repository;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();

    Role getRoleById(Long id);

    Role getRoleByName(String name);
}
