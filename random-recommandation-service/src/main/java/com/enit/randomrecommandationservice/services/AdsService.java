package com.enit.randomrecommandationservice.services;

import com.enit.randomrecommandationservice.entity.Ad;

import java.util.List;
import java.util.Optional;



public interface AdsService {

	public void saveAd(Ad ad);

	public void saveAllAds(List<Ad> ads);

	public Iterable<Ad> findAllAds();

	public Optional<Ad> findById(String id);
	
	public List<Ad> getAllAds();

	public String deleteAd(String id);

	public void deleteAllAds();

}
