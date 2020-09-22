package com.enit.preferencesRec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enit.preferencesRec.entity.User;
import com.enit.preferencesRec.entity.UserProfile;



@Repository
public interface UserRepository extends MongoRepository<User, String> {



//     public List<Ad> findByRequestId(String id);

}
