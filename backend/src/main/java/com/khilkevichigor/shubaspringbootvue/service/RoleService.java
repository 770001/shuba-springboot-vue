package com.khilkevichigor.shubaspringbootvue.service;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import com.khilkevichigor.shubaspringbootvue.model.User;

import java.util.List;

/**
 *
 */
public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role getRoleByName(String name);

    Role addRole(Role role);
//    Role updateRole(User user);
//    void deleteRole(Long id);
}
