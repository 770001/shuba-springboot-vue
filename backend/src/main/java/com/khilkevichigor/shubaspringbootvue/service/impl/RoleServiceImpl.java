package com.khilkevichigor.shubaspringbootvue.service.impl;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.repository.RoleRepository;
import com.khilkevichigor.shubaspringbootvue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

//    @Override
//    public Role updateRole(User user) {
//        return null;
//    }
//
//    @Override
//    public void deleteRole(Long id) {
//
//    }
}
