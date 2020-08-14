package com.enit.randomrecommandationservice.repository;

import com.enit.randomrecommandationservice.entity.Ad;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdsRepository extends MongoRepository<Ad, String> {

}