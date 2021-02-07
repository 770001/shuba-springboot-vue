package com.khilkevichigor.shubaspringbootvue.service.impl;

import com.khilkevichigor.shubaspringbootvue.exception.UserNotFoundException;
import com.khilkevichigor.shubaspringbootvue.model.User;
import com.khilkevichigor.shubaspringbootvue.repository.UserRepository;
import com.khilkevichigor.shubaspringbootvue.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    //    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private RoleService roleService;

    //через конструктор ошибка
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Autowired
//    public void setRoleRepository(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //взять всех юзеров
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public List<Role> getAllRoles() {
//        return roleRepository.findAll();
//    }

//    public Role getRoleById(Long id) {
//        return roleRepository.getRoleById(id);
//    }

    //взять юзера по id
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("Пользователь с id = " + id + " не может быть найден в базе данных.");
        }
    }

    //удалить юзера
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Создаем юзера. Передаем юзера и массив ролей
     */
    public User addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        //проверка имени
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                newUser.setUsername(user.getUsername() + "_copy");
            }
        }
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Редактируем юзера. Обновляем старый пароль и массив ролей
     */
    public User updateUser(User editUser) {
        //проверка пароля
        User oldUser = getUserById(editUser.getId());
        String oldPassword = oldUser.getPassword();
        String newPassword = editUser.getPassword();
        //Доп. проверка. Если новый пароль от 3 до 5 символов - берем его
        if (newPassword.length() > 2 && newPassword.length() < 6) {
            editUser.setPassword(passwordEncoder.encode(newPassword));
        } else {
            editUser.setPassword(oldPassword);
        }

        //проверка имени
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(editUser.getUsername())) {
                editUser.setUsername(oldUser.getUsername());
            }
        }
        userRepository.save(editUser);
        return editUser;
    }

    //достаем юзера по username
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
