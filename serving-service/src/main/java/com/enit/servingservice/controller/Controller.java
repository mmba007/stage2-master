package com.enit.servingservice.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.enit.servingservice.entity.Ad;
import com.enit.servingservice.entity.ConsumerRequest;
import com.enit.servingservice.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@CrossOrigin(origins = "*")
@RestController
public class Controller {
	@Autowired
	KafkaTemplate<String, ConsumerRequest> kafkaTemplate;
	@Autowired
	AdsService adsService;
	int i = 0;
	String id = "0";

	@GetMapping(value = "/request/{username}/{lat}/{lon}")
	public List<Ad> saveConsumer(@PathVariable String username ,@PathVariable Double lon,@PathVariable Double lat ) throws InterruptedException {
		// first check if user is logged in by his id(or username) cad listen to
		// login/auth kafka topic for
		// loggedin users.
//		final String url = "http://192.168.99:8083/check/user/status/" + username;
//		Boolean status = new RestTemplate().getForObject(url, Boolean.class);

//		if (true) {
			i++;
			id = Integer.toString(i);
			Double[] location=new Double[]{lat,lon};
			ConsumerRequest consumerRequest = new ConsumerRequest(id ,username,lat,lon);
			kafkaTemplate.send("requestRecommandation", consumerRequest);

			Thread.sleep(700);
//		System.out.println( adsService.findByRequestId(id));
//		Set<Ad> allAds = new HashSet<Ad>();
//		List<Ad> ads = adsService.findByRequestId(id);
//		allAds.addAll(ads);
//		ads=adsService.findAllAds();
//		allAds.addAll(ads);

//		return adsService.findByRequestId(id);
			return adsService.findByTheGreaterRequestId(id);
//		} else {
//			return null;
//		}

	}

}
