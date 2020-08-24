package com.enit.adscrud.services;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.enit.adscrud.entity.Ad;
import com.enit.adscrud.entity.Status;
import com.enit.adscrud.repository.AdsRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AdsServiceImpl implements AdsService {

	private static final String URL_IMAGE_DIRECTORY = "http://localhost:8080/sendImageRootPath";

	@Autowired
	private AdsRepository edao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springboot.elasticsearch.service.Adsserv#saveAds(java.util.List)
	 */

	@Override
	public Iterable<Ad> findByEmail(String email) {
		return edao.findByAdvertiserEmail(email);
	}

	@Override
	public List<Ad> getActiveAds(String email) {
		Iterable<Ad> ads = edao.findByAdvertiserEmail(email);
		List<Ad> activeAds = new ArrayList<Ad>();
		for (Ad ad : ads) {
			System.out.println("///////////////// ad found : " + ad.toString());
			if (ad.getStatus().equals("Active")) {
				activeAds.add(ad);
			}
		}
		return activeAds;
	}

	@Override
	public List<Ad> getDisabledAds(String email) {
		Iterable<Ad> ads = edao.findByAdvertiserEmail(email);
		List<Ad> disabledAds = new ArrayList<Ad>();
		for (Ad ad : ads) {
			if (ad.getStatus().equals("Disabled")) {
				disabledAds.add(ad);
			}
		}
		return disabledAds;
	}

	@Override
	public String saveOneAd(Ad ad) {
//		RestTemplate restTemplate = new RestTemplate();
//		String imageRootPath = restTemplate.getForObject(URL_IMAGE_DIRECTORY, String.class);
//		ad.setPostedOn(new Date());
//		ad.setStatus("Active");
//		float sumRate = 0;
//		if (!ad.getRates().isEmpty()) {
//			for (float f : ad.getRates()) {
//				sumRate += f;
//			}
//			sumRate = sumRate / ad.getRates().size();
//		}
//		ad.setRate(sumRate);
//
//		StringBuilder someImageFile = new StringBuilder(ad.getPhotosUrls().get(0));
//		String imagesDirectory = someImageFile.substring(0, someImageFile.lastIndexOf("/"));
//		ad.setAdImagesDirectory(imageRootPath + imagesDirectory);
		Ad savedAd = edao.save(ad);
		return savedAd.getId();
	}

//	@Override
//	public void saveOneAd(Ads ad, String username) {
//		RestTemplate restTemplate = new RestTemplate();
//		String imageRootPath = restTemplate.getForObject(URL_IMAGE_DIRECTORY, String.class);
//
////		http://localhost:8097/api/user/{username}/update/publishedAds"
//		ad.setPostedOn(new Date());
//		ad.setStatus("Active");
//		StringBuilder someImageFile = new StringBuilder(ad.getPhotosUrls().get(0));
//		String imagesDirectory = someImageFile.substring(0, someImageFile.lastIndexOf("/"));
//		ad.setAdImagesDirectory(imageRootPath + imagesDirectory);
//		Ads savedAd = edao.save(ad);
//		final String URL_UPDATE_ADVERTISER_PUBLISHED_ADS = "http://localhost:8098/api/user/" + username
//				+ "/update/publishedAds/" + savedAd.getId();
//		restTemplate.getForObject(URL_UPDATE_ADVERTISER_PUBLISHED_ADS, String.class);
//	}

	@Override
	public void saveManyAds(List<Ad> ads) {
		for (Ad ad : ads) {
			ad.setPostedOn(new Date());
			ad.setStatus(Status.ACTIVE);
		}
		edao.saveAll(ads);
	}

	@Override
	public Optional<Ad> findById(String id) {
		return edao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springboot.elasticsearch.service.Adsserv#findAllAdss()
	 */
	@Override
	public Iterable<Ad> findAllAds() {
		return edao.findAll();
	}

	@Override
	public String deleteOneAd(Ad ad) {
		// TODO Auto-generated method stub
		edao.delete(ad);
		return " Ad deleted";
	}

	@Override
	public void updateAd(Ad ad) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void deleteAllAds() {
//		edao.deleteAll();
//	}

	@Override
	public void deleteAllAds() {
		try {
			Iterable<Ad> ads = findAllAds();
			for (Ad ad : ads) {
				if (ad.getAdImagesDirectory() != null) {
					File directory = new File(ad.getAdImagesDirectory());
					if (directory.exists()) {
						FileUtils.cleanDirectory(directory);
						FileUtils.deleteDirectory(directory);
						System.out.println("///////ad num . directory deleted ");
					}

				}
			}
			edao.deleteAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

	}

	@Override
	public Boolean deleteAd(String id) {
		boolean flag = false;

		try {
			Iterable<Ad> ads = findAllAds();
			for (Ad ad : ads) {
				if (ad.getId().equals(id)) {

					if (ad.getAdImagesDirectory() != null) {
						File directory = new File(ad.getAdImagesDirectory());
						if (directory.exists()) {
							FileUtils.cleanDirectory(directory);
							FileUtils.deleteDirectory(directory);
							System.out.println("///////ad directory deleted ");
						}
					}
					flag = true;

				}
			}
			if (flag) {
				edao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

//	@Override
//	public String getAdPublisherFolderDirectory(String id) {
//		String publisherFolderDirectory = "";
//		Iterable<Ad> ads = findAllAds();
//		for (Ad ad : ads) {
//			if (ad.getId().equals(id)) {
//				if (ad.getAdImagesDirectory() != null) {
//					publisherFolderDirectory = ad.getAdImagesDirectory().substring(0,
//							ad.getAdImagesDirectory().lastIndexOf("/"));
//					System.out.println("***************** publisherFolderDirectory : " + publisherFolderDirectory);
//				}
//			}
//		}
//		if (!publisherFolderDirectory.isBlank()) {
//			return publisherFolderDirectory;
//		} else {
//			return "error";
//		}
//
//	}

}
