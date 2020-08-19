package com.enit.servingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.enit.servingservice.entity.Ad;
import com.enit.servingservice.repository.RecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AdsServiceImp implements AdsService {

	@Autowired
	private RecRepository edao;

	@Override
	public void saveAd(Ad ad) {
		edao.save(ad);
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
	public List<Ad> findAllAds() {
		return edao.findAll();
	}

	@Override
	public List<Ad> findByRequestId(String id) {

		return edao.findByRequestId(id);

	}

	@Override
	public List<Ad> findByTheGreaterRequestId(String id) {
		List<Ad> result = new ArrayList<Ad>();
		List<Ad> allAds = edao.findAll();
		for (Ad ad : allAds) {
			if (ad.getRequestId().equals(id)) {
				result.add(result.size(), ad);
			}
		}
		for (Ad ad : allAds) {
			if (!ad.getRequestId().equals(id)) {
				result.add(result.size(), ad);
			}
		}
		return result;
//		List edao.findByRequestId(id);

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
