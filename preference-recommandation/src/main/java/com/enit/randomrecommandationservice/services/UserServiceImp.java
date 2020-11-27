package com.enit.randomrecommandationservice.services;

import com.enit.randomrecommandationservice.entity.User;

import com.enit.randomrecommandationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private UserRepository edao;

	@Override
	public void saveUser(User user) {
		edao.save(user);
		System.out.println("saved successfully");
	}


	@Override
	public void saveAllUsers(List<User> users) {
		edao.saveAll(users);
	}

	@Override
	public Optional<User> findById(String id) {
		return edao.findById(id);
	}



	@Override
	public Iterable<User> findAllUsers() {
		return edao.findAll();
	}

	@Override
	public String deleteUser(String id) {
		edao.deleteById(id);
		return " User deleted";
	}

	@Override
	public void deleteAllUsers() {
		edao.deleteAll();
	}




}
