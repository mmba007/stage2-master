package com.enit.randomrecommandationservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enit.randomrecommandationservice.entity.Ad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.enit.randomrecommandationservice.repository.AdsRepository;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service

public class AdsServiceImp implements AdsService {
	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private AdsRepository edao;

	@Override
	public void saveAd(Ad ad) {
		edao.save(ad);
		System.out.println("saved successfully");
	}
	@Override
	public List<Ad> findByLocationNear(Point p, Distance d){
		Circle area=new Circle(p,d);
		Query q=new Query();
		q.addCriteria(Criteria.where("location").withinSphere(area));
		//longitude          //latitud
		return mongoOperations.find(q,Ad.class);
		
//		return edao.findByLocationNear(p,d);
	}

	@Override
	public void saveAllAds(List<Ad> ads) {
		edao.saveAll(ads);
	}

	@Override
	public Optional<Ad> findById(String id) {
		return edao.findById(id);
	}

	@Override
	public List<Ad> getAllAds() {
		final List<Ad> ads = new ArrayList<>();
        edao.findAll().forEach(Ads -> ads.add(Ads));
        return ads;
	}
    @Override
	public Ad findone(){
		return mongoOperations.findOne(new Query(where("category").is("car")), Ad.class);
	}
			;

	@Override
	public Iterable<Ad> findAllAds() {
		return edao.findAll();
	}

	@Override
	public String deleteAd(String id) {
		edao.deleteById(id);
		return " Ad deleted";
	}

	@Override
	public void deleteAllAds() {
		edao.deleteAll();
	}

}
