package com.simple.demo.repository;

import com.simple.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAll();
}
