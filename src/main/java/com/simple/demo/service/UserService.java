package com.simple.demo.service;

import com.simple.demo.entity.User;

import java.util.List;

public interface UserService {
    boolean existUserByUserNameAndLastName(String firstName, String lastName);

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    void createUser(User user);
    
    void initialUser();

    List<User> findAllUser();

    void delete(Long id);
}
