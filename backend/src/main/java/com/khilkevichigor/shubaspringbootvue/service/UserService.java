package com.khilkevichigor.shubaspringbootvue.service;


import com.khilkevichigor.shubaspringbootvue.model.User;

import java.util.List;

public interface UserService {

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    User addUser(User user); //сохранить с ролями

    User findUserByUsername(String username); //достать по username
}