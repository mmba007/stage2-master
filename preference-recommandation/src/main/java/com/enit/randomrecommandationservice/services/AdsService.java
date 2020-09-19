package com.enit.randomrecommandationservice.services;

import com.enit.randomrecommandationservice.entity.Ad;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.Optional;



public interface AdsService {

	public void saveAd(Ad ad);

	public Ad findone();

	List<Ad> findByLocationAndPreferences(double d,List<String> preferences,double largitude,double longitude);

	public void saveAllAds(List<Ad> ads);

	public Iterable<Ad> findAllAds();

	public Optional<Ad> findById(String id);
	
	public List<Ad> getAllAds();

	public String deleteAd(String id);

	public void deleteAllAds();

	public List<Ad> findByLocationNear(Point p, Distance d);

}
