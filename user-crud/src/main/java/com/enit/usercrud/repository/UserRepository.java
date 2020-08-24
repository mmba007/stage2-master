package com.enit.usercrud.repository;

import java.util.Optional;

import com.enit.usercrud.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	String deleteByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}