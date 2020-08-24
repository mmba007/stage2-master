package com.enit.authentication.repository;

import com.enit.authentication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    String deleteByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

