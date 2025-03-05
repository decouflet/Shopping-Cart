package com.microservice.user.msvc_user.service;

import com.microservice.user.msvc_user.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void deleteById(Long id);
    User findByNameAndPassword(String name, String password);
    void addSpent(BigDecimal cartAmount, Long user_id);
}
