package controller;

import java.util.Optional;

import entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.UserProfileRepository;

import services.AdsService;


@RestController
public class AdsController {

	@Autowired
	AdsService adService;

	@Autowired
	UserProfileRepository userProfileRepo;

//	@Autowired
//	UserStatusRepository userStatusRepo;

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
