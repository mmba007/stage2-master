package com.enit.randomrecommandationservice.services;

import com.enit.randomrecommandationservice.entity.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Optional;


public interface UserService {

	public void saveUser(User user);


	public void saveAllUsers(List<User> users);

	public Iterable<User> findAllUsers();

	public Optional<User> findById(String username);


	public String deleteUser(String id);

	public void deleteAllUsers();


}
