package com.enit.adscrud.services;

import com.enit.adscrud.entity.Ad;

import java.util.List;
import java.util.Optional;


public interface AdsService {
	public void saveManyAds(List<Ad> ads);

	public Iterable<Ad> findAllAds();

	public Optional<Ad> findById(String id);

	public String saveOneAd(Ad ad);

	public String deleteOneAd(Ad ad);

	public void deleteAllAds();

	public List<Ad> getActiveAds(String email);

	public List<Ad> getDisabledAds(String email);

	public Boolean deleteAd(String id);

//	public String getAdPublisherFolderDirectory(String id);

	public void updateAd(Ad ad);

	public Iterable<Ad> findByEmail(String email);
}
