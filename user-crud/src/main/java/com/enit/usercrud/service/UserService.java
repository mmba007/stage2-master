package com.enit.usercrud.service;

import com.enit.usercrud.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    String deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    String  updateUser(String username,User user);

    String  saveUser(String username,User user);

}
