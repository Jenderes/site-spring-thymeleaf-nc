package com.simple.demo.service;

import com.simple.demo.entity.User;
import com.simple.demo.model.SearchDto;

import java.util.List;

public interface UserService {
    boolean existUser(SearchDto searchDto);

    User findUser(SearchDto searchDto);

    void createUser(User user);
    
    void initialUser();

    List<User> findAllUser();

    void delete(Long id);
}
