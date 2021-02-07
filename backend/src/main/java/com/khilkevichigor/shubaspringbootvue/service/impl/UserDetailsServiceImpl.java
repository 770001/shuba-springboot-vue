package com.khilkevichigor.shubaspringbootvue.service.impl;

import com.khilkevichigor.shubaspringbootvue.model.Role;
import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Связываем {@link UserServiceImpl} со Spring Security через имплементацию {@link UserDetailsService}
 * Здесь основная логика аутентификации с использованием Spring Security.
 * GrantedAuthority - это права(разрешения) пользователя на основе его ролей.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    //сетим пользователю права на основе ролей
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        //получаем юзера по username
        User user = userService.findUserByUsername(username);

        //если юзер не найден
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        } else {
            //создаем права на основе ролей
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (Role role : user.getRoles()) { //для всех ролей юзера
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            //возвращаем спринговского юзера с нашими деталями (username, password и правами)
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
    }
}

