package com.enit.servingservice.service;

import com.enit.servingservice.entity.Ad;

import java.util.List;
import java.util.Optional;


public interface AdsService {

	public void saveAd(Ad ad);

	public void saveAllAds(List<Ad> ads);

	public List<Ad> findAllAds();

	public Optional<Ad> findById(String id);
	
	public List<Ad> findByRequestId(String id);
	
	public List<Ad> findByTheGreaterRequestId(String id);
	
	public List<Ad> getAllAds();

	public String deleteAd(String id);

	public void deleteAllAds();

}
