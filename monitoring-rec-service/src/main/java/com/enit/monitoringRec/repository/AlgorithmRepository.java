package com.enit.monitoringRec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enit.monitoringRec.entity.Algorithm;



@Repository
public interface AlgorithmRepository extends MongoRepository<Algorithm, String> {



//     public List<Ad> findByRequestId(String id);

}
