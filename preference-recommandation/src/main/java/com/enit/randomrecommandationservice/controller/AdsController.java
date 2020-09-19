package com.enit.randomrecommandationservice.controller;

import java.util.List;
import java.util.Optional;

import com.enit.randomrecommandationservice.entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.enit.randomrecommandationservice.repository.UserProfileRepository;

import com.enit.randomrecommandationservice.services.AdsService;


@RestController
public class AdsController {

	@Autowired
	AdsService adService;

	@Autowired
	UserProfileRepository userProfileRepo;

//	@Autowired
//	UserStatusRepository userStatusRepo;




//	@GetMapping(value = "/test/{d}")
//   public List<Ad> test(@PathVariable double d)
//   {
////	   return adService.findByLocationNear(new Point(36.88,10.099),new Distance(100, Metrics.KILOMETERS));
//     return adService.findByLocationAndPreferences(d);
//
//   }

	@GetMapping(value = "/ad/{id}")
	public Optional<Ad> getAd(@PathVariable String id) {
		return adService.findById(id);
	}

	@GetMapping(value = "/getall")
	public Iterable<Ad> getAllAds() {
		return adService.findAllAds();
	}

//	@GetMapping(value = "/check/user/status/{username}")
//	public Boolean checkIfUserIsLoggedIn(@PathVariable String username) {
//		if (userStatusRepo.existsById(username)) {
//			System.out.println("\n\n*************************************** user exist in logged in users *******************************\n\n");
//			return true;
//		} else {
//			System.out.println("\n\n******************************* user doesn't exist in logged in users *********************************\n\n");
//			return false;
//		}
//	}

}
