package com.enit.randomrecommandationservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enit.randomrecommandationservice.entity.Ad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
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

	//List<String> preferences, List<Double> point
	@Override
	public List<Ad> findByLocationAndPreferences(double d,List<String> preferences,double largitude,double longitude) {
		Point location = new Point(largitude, longitude);

		NearQuery query = NearQuery.near(location).maxDistance(new Distance(d, Metrics.MILES));
		AggregationOperation match0=Aggregation.geoNear(query,"location");
;
		AggregationOperation match = Aggregation.match(Criteria.where("category").in(preferences));
		Aggregation aggregation = Aggregation.newAggregation(match0,Aggregation.unwind("category",true),match);

		System.out.println("Aggregation = "+aggregation);
//		Aggregation output = mongoOperations.aggregate(aggregation);
//		System.out.println("output 	= "+output.getMappedResults());

		AggregationResults<Ad> output = mongoOperations.aggregate(aggregation,Ad.class, Ad.class);
		List<Ad> result=output.getMappedResults();
		return result;
//		AggregationOperation unwind = Aggregation.unwind("myDetails");
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
