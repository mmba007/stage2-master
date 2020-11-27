package com.enit.adscrud.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;


import com.enit.adscrud.config.EventService;
import com.enit.adscrud.entity.Ad;
import com.enit.adscrud.entity.Status;
import com.enit.adscrud.events.DeleteAdEvent;
import com.enit.adscrud.events.Event;

import com.enit.adscrud.events.SaveAdEvent;
import com.enit.adscrud.events.UpdateAdEvent;
import com.enit.adscrud.repository.AdsRepository;
import com.enit.adscrud.services.AdsService;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@CrossOrigin(origins = "*")
@RestController
public class AdsController {

	@Autowired
	AdsService eserv;

	@Autowired
	AdsRepository repo;

	private final EventService kafkaTemplate;

	public  AdsController(EventService eventService){
		this.kafkaTemplate=eventService;
	}
//	@Autowired
//	KafkaTemplate<String, Event> kafkaTemplate;



//	@Autowired
//	SearchBySuggest searchBySuggest;
//
//	@Autowired
//	private SearchQueryBuilder searchQueryBuilder;

//	@GetMapping(value = "/searchBySuggest/{preference}")
//	public Set<String> getSearchBySuggestResult(@PathVariable String preference) throws Exception {
//		return searchBySuggest.suggestSearch(preference);
//	}
//
//	@GetMapping(value = "/searchByAllMeans/{preference}")
//	public Set<String> getSearchByAllMeansResult(@PathVariable String preference) {
//		return searchQueryBuilder.getAll(preference);
//	}

	@PostMapping(value = "/ad/save")
	@Transactional
	public String saveAds(@RequestBody Ad ad) {



		String savedAdId = eserv.saveOneAd(ad);
		kafkaTemplate.sendEvent(new SaveAdEvent(ad));
//		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(ad));
		System.out.println("saved ad's id is :" + savedAdId);
		return savedAdId;
	}
//    @GetMapping(value="/test")
//	@Transactional
//	public String test()
//	{
//	 eserv.saveOneAd(new Ad("2","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,12.098655)));
//		kafkaTemplate.sendEvent( new SaveAdEvent(new Ad("2","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,12.098655))));
////		eserv.saveOneAd(new Ad("3","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(37.800115,12.097655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("3","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(37.800115,12.0976655))));
////		eserv.saveOneAd(new Ad("4","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(38.800115,10.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("4","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(38.800119,12.099655))));
////		eserv.saveOneAd(new Ad("4","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(39.800119,12.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("4","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(39.800115,10.099655))));
////		eserv.saveOneAd(new Ad("5","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("5","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655))));
////		eserv.saveOneAd(new Ad("6","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,20.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("6","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,20.099655))));
////		eserv.saveOneAd(new Ad("7","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,15.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("7","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,15.099655))));
////		eserv.saveOneAd(new Ad("8","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(39.800115,11.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("8","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(39.800115,11.099655))));
////		eserv.saveOneAd(new Ad("9","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(44.800115,15.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("9","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(4.800115,15.099655))));
////		eserv.saveOneAd(new Ad("10","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(30.800115,14.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("10","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(30.800115,14.099655))));
////		eserv.saveOneAd(new Ad("11","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(29.800115,15.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("11","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(29.800115,15.099655))));
////		eserv.saveOneAd(new Ad("12","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("12","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655))));
////		eserv.saveOneAd(new Ad("13","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,11.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("13","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,11.099655))));
////		eserv.saveOneAd(new Ad("14","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,12.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("14","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,12.099655))));
////		eserv.saveOneAd(new Ad("15","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("15","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,14.099655))));
////		eserv.saveOneAd(new Ad("16","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,13.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("16","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,13.099655))));
////		eserv.saveOneAd(new Ad("17","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,15.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("17","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,17.099655))));
////		eserv.saveOneAd(new Ad("18","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,17.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("18","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,17.099655))));
////		eserv.saveOneAd(new Ad("19","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,19.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("19","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,19.099655))));
////		eserv.saveOneAd(new Ad("20","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,15.099655)));
////		kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(new Ad("20","car","mercedes amg c63 for sale","clean car for sale in dubai 60,000 km",100000,25691503,"Dubai","Dubai","Dubai", Status.ACTIVE,"c","clean","2018","mercedes",100,4,new GeoPoint(36.800115,15.099655))));
//
//
//
//
//
//	return "test passed successfully";
//	}
//
//	@PostMapping(value = "/ads/saveall")
//	public String saveAllAds(@RequestBody List<Ad> ads) {
//
//		eserv.saveManyAds(ads);
//		for (Ad ad : ads)
//			kafkaTemplate.send("adsToBeConsumed", new SaveAdEvent(ad));
//		// for(Ads Ads : myAdss)
//		// kafkaTemplate.send("exemple","hello again");
//
//		return "Records saved in the db.";
//	}
//
//
//	@GetMapping(value="/test")
//	public  String test()
//	{
//		return "test passed successfully";
//	}
	@GetMapping(value = "/ad/get/{id}")
	public Optional<Ad> getAds(@PathVariable String id) {
		Optional<Ad> ad = eserv.findById(id);
		return ad;
	}
//
	@GetMapping(value = "/ad/{email}/{id}")
	public Ad getAdById(@PathVariable String email, @PathVariable String id) {

		Iterable<Ad> Ads = eserv.findByEmail(email);

		for (Ad ad : Ads) {
			if (ad.getId().equals(id)) {
				System.out.println("ad with id: " + id + " is found:  " + ad);
				return ad;
			}
		}
		System.out.println("ad with id: " + id + " is not found ///////////////////");
		return null;
	}

	@GetMapping(value = "/ads/{email}")
	public Iterable<Ad> getAdsByEmail(@PathVariable String email) {
		System.out.println("email is: " + email);
		Iterable<Ad> ads = repo.findByAdvertiserEmail(email);
		return ads;
	}
//
	@GetMapping(value = "/activeAds/{email}")
	public Iterable<Ad> getActiveAdsByEmail(@PathVariable String email) {
		System.out.println("email is: " + email);
		List<Ad> ads = eserv.getActiveAds(email);
		return ads;
	}
//
	@GetMapping(value = "/activeThenDisabledAds/{email}")
	public Iterable<Ad> getActiveBeforeDisabledAdsByEmail(@PathVariable String email) {
		System.out.println("email is: " + email);
		List<Ad> ads = eserv.getActiveAds(email);
		ads.addAll(eserv.getDisabledAds(email));
		return ads;
	}

	@GetMapping(value = "/ads/getall")
	public Iterable<Ad> getAllAds() {
		Iterable<Ad> ads = eserv.findAllAds();
		for (Ad ad : ads) {
			System.out.println(ad);
		}
//		System.out.println(eserv.findAllAds());
		return eserv.findAllAds();
	}
//
	@GetMapping(value = "/ad/{id}/views/increment")
	@Transactional
	public String incrementAdViews(@PathVariable String id) {
		Ad ad = eserv.findById(id).get();
		if (ad != null) {
			ad.setViews(ad.getViews() + 1);
			eserv.saveOneAd(ad);
			kafkaTemplate.sendEvent( new UpdateAdEvent(ad));

			//kafkaTemplate.send("adsToBeConsumed", new UpdateAdEvent(ad));
			return "Ad's views incremented by 1.";
		} else {
			return "Ad with id " + id + " doesn't exist";
		}
	}
//
	@PutMapping(value = "/ad/update/{id}")
	@Transactional
	public String updateAd(@RequestBody Ad ad, @PathVariable String id) {
		System.out.println("update is called // Ad is : " + ad.toString());

		Ad a = eserv.findById(id).get();
		if (a!=null) {
			eserv.saveOneAd(ad);
			kafkaTemplate.sendEvent( new UpdateAdEvent(ad));
			return "Ad updated successfully.";
		} else
			return "Ad doesn't exist";
	}
//
	@GetMapping(value = "/ad/disable/{id}")
	@Transactional
	public String disableAd(@PathVariable String id) {
		Ad ad = eserv.findById(id).get();
		if (ad != null) {
			ad.setStatus(Status.DESACTIVE);
//			eserv.saveOneAds(ad);
			repo.save(ad);
			kafkaTemplate.sendEvent(new UpdateAdEvent(ad));
			return "Ad disabled successfully.";
		} else
			return "Ad doesn't exist";
	}

	@GetMapping(value = "/ad/reactivate/{id}")
	@Transactional
	public String reactivateAd(@PathVariable String id) {
		Ad ad = eserv.findById(id).get();
		if (ad != null) {
			ad.setStatus(Status.ACTIVE);
//			eserv.saveOneAds(ad);
			repo.save(ad);
			kafkaTemplate.sendEvent( new UpdateAdEvent(ad));
			return "Ad reactivated successfully.";
		} else
			return "Ad doesn't exist";
	}
//
	@DeleteMapping(value = "/ad/delete/{id}")
	public ResponseEntity<String> deleteAd(@PathVariable String id) {
		String message = "";

		if (eserv.deleteAd(id)) {

			kafkaTemplate.sendEvent( new DeleteAdEvent(id));

			System.out.println("******** ad with id " + id + " deleted");
			message = "Ad deleted successfully";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			message = "A problem has occured when deleting ad and/or ad's directory!!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
//		return "ad with id: " + id + " deleted";
	}
//
//	@PostMapping(value = "/publishedAds/delete")
//	public String deletePulishedAd(@RequestBody List<String> ids) {
//		for (int i = 0; i < ids.size(); i++) {
//			if (i == ids.size() - 1) {
//				//String publishedFolderDirectory = eserv.getAdPublisherFolderDirectory(ids.get(i));
//				deleteAd(ids.get(i));
//				//deletePublisherFolderDirectory(publishedFolderDirectory);
//			} else {
//				deleteAd(ids.get(i));
//			}
//		}
//		return "All published Ads have been deleted";
//	}
//
////	@GetMapping(value = "/ads/getPhotosPublisherDirectory/{id}")
////	public String getFolderThatContainsAdsPhotos(@PathVariable String id) {
////		//return eserv.getAdPublisherFolderDirectory(id);
////
////	}
//
//	public void deletePublisherFolderDirectory(String directory) {
//		try {
//
//			System.out.println("***************** publisherFolderDirectory : " + directory);
//			File directoryFile = new File(directory);
//			if (directoryFile.exists()) {
//				FileUtils.cleanDirectory(directoryFile);
//				FileUtils.deleteDirectory(directoryFile);
//				System.out.println("///////publisher ads photos folder deleted ");
//
//			}
//
//		} catch (IOException e) {
//			System.out.println(e);
//
//		}
//	}
//
////	@GetMapping(value = "/Ad/delete/{id}")
////	public String deleteOneAd(@PathVariable String id) {
////		String message = "";
////		if (eserv.deleteAd(id)) {
////			message = "Ad deleted successfully";
////			return message;
////		} else {
////			message = "A problem has occured when deleting ad and/or ad's directory!!";
////			return message;
////		}
////	}
//
	@DeleteMapping(value = "/ads/deleteall")
	@Transactional
	public String deleteManyAds() {
//		eserv.deleteAllAds();
////		repo.deleteAll();
//		return "All ads have been deleted";
		Iterable<Ad> existantAds = eserv.findAllAds();
		if (existantAds != null) {
			eserv.deleteAllAds();
			for (Ad ad : existantAds) {
				kafkaTemplate.sendEvent( new DeleteAdEvent(ad.getId()));
			}
			return "All ads have been deleted successfully.\n";
		} else {
			return "No ads found.";
		}
	}
//
//	@PostMapping(value = "/ad/{username}/save")
//	public String saveAd(@RequestBody Ads ad, @PathVariable String username) {
////		ad.setAdImagesDirectory(IMAGES_DIRECTORY);
//		eserv.saveOneAd(ad, username);
//
//		return "Record saved in the db.";
//	}
//
////	@GetMapping("/recieveAdImagesDirectory")
////	public String getAdImagesDirectory(String directory) {
////		IMAGES_DIRECTORY = directory;
////		return directory;
////	}
//
////	@DeleteMapping(value = "/shiftdeleteall")
////	public String deleteManyAdsEntirely() {
////		eserv.deleteAllAdsEntirely();
////		return "All ads have been deleted";
////	}
//
//	// used when deleting all advertiser's published ads
//
//	/*
//	 * @DeleteMapping(value="/delete/Ads/{id}") public String
//	 * delete(@PathVariable("id") String id) { Optional<Ads> Ads =
//	 * eserv.findById(id); if(Ads.get()!=null) { eserv.deleteAds(Ads.get());
//	 * kafkaTemplate.send("exemple",new
//	 * SaveAdsEvent(Ads.getUser_id()'',Ads.getUser_preference()));
//	 *
//	 * return "deleted"; } else return "not exist"; } /** Method to fetch the Ads
//	 * details on the basis of designation.
//	 *
//	 * @param designation
//	 *
//	 * @return
//	 */

}
