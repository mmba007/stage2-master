package com.enit.preferencesRec.services;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.enit.preferencesRec.entity.User;

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
