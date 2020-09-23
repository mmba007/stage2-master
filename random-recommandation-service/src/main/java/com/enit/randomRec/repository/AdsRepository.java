package com.enit.randomRec.repository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.enit.randomRec.entity.Ad;

import java.util.List;


@Repository
public interface AdsRepository extends MongoRepository<Ad, String> {
//    List<Ad> findByLocationNear(Point p, Distance d);
}