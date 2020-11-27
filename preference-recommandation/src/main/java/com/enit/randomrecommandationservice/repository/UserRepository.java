package com.enit.randomrecommandationservice.repository;

import com.enit.randomrecommandationservice.entity.User;
import com.enit.randomrecommandationservice.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MongoRepository<User, String> {



//     public List<Ad> findByRequestId(String id);

}