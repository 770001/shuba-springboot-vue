package com.khilkevichigor.shubaspringbootvue.service;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.repository.RoleRepository;
import com.khilkevichigor.shubaspringbootvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitDbService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    /**
     * Init dataBase username: admin, password: admin, roles: ROLE_ADMIN
     */
    @PostConstruct
    public void initDb() {
        Role role = roleRepository.getRoleByName("ROLE_ADMIN");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }
        assert roleRepository.getRoleByName("ROLE_ADMIN") != null;

        role = roleRepository.getRoleByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
        assert roleRepository.getRoleByName("ROLE_USER") != null;

        User user = userRepository.findUserByUsername("admin");
        if (user == null) {
            user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = roleRepository.getRoleByName("ROLE_ADMIN");
            Role roleUser = roleRepository.getRoleByName("ROLE_USER");
            roles.add(roleAdmin);
            roles.add(roleUser);
            user.setRoles(roles);
            userRepository.save(user);
        }
        User admin = userRepository.findUserByUsername("admin");
        assert admin != null;
        Set<Role> roles = admin.getRoles();
        assert roles.size() == 2;
    }
}
