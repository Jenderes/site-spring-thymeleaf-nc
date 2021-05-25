package com.simple.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.demo.entity.User;
import com.simple.demo.model.SearchDto;
import com.simple.demo.repository.UserRepository;
import com.simple.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean existUser(SearchDto searchDto) {
        return false;
    }

    @Override
    public User findUser(SearchDto searchDto) {
        return userRepository.findUserByFirstNameAndLastName(searchDto.getFirstName(), searchDto.getLastName());
    }

    @Override
    public void createUser(User createUser) {
        userRepository.save(createUser);
    }

    @Override
    public void initialUser() {
        try {
            User[] users = objectMapper.readValue(new File("src/main/resources/json/initial-users.json"), User[].class);
            for (User user: users){
                if (userRepository.findUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()) == null){
                    userRepository.save(user);
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
    @Override
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
