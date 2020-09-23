package com.enit.randomRec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enit.randomRec.entity.UserProfile;



@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

	/**
	 * Method to fetch the employee details on the basis of designation by using
	 * Elastic-Search-Repository.
	 * 
	 * @param designation
	 * @return
	 */

//     public List<Ad> findByRequestId(String id);

}