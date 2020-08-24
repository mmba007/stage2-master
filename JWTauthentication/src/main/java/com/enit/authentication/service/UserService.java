package com.enit.authentication.service;


import com.enit.authentication.model.User;

import java.util.Optional;


public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    String deleteByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    String  updateUser(String username,User user);

    String  saveUser(String username,User user);

}
