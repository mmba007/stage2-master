package com.enit.randomrecommandationservice.repository;

import com.enit.randomrecommandationservice.entity.Ad;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdsRepository extends MongoRepository<Ad, String> {
//    List<Ad> findByLocationNear(Point p, Distance d);




}