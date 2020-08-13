package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdsRepository;


@Service

public class AdsServiceImp implements AdsService {

	@Autowired
	private AdsRepository edao;

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
