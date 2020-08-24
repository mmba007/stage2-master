package com.enit.adscrud.repository;

import com.enit.adscrud.entity.Ad;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdsRepository extends ElasticsearchRepository<Ad, String> {
	public Iterable<Ad> findByAdvertiserEmail(String email);
}