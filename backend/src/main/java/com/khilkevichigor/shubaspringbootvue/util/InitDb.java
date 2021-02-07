package com.khilkevichigor.shubaspringbootvue.util;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.repository.RoleRepository;
import com.khilkevichigor.shubaspringbootvue.repository.UserRepository;
import com.khilkevichigor.shubaspringbootvue.service.RoleService;
import com.khilkevichigor.shubaspringbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitDb {

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    /**
     * Init dataBase username: admin, password: admin, roles: ROLE_ADMIN
     */
    @PostConstruct
    public void initDb() {
        Role role = roleService.getRoleByName("ROLE_ADMIN");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_ADMIN");
            roleService.addRole(role);
        }
        assert roleService.getRoleByName("ROLE_ADMIN") != null;

        role = roleService.getRoleByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleService.addRole(role);
        }
        assert roleService.getRoleByName("ROLE_USER") != null;

        User user = userService.findUserByUsername("admin");
        if (user == null) {
            user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = roleService.getRoleByName("ROLE_ADMIN");
            Role roleUser = roleService.getRoleByName("ROLE_USER");
            roles.add(roleAdmin);
            roles.add(roleUser);
            user.setRoles(roles);
            userService.addUser(user);
        }
        User admin = userService.findUserByUsername("admin");
        assert admin != null;
        Set<Role> roles = admin.getRoles();
        assert roles.size() == 2;
    }
}
