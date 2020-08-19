package com.enit.servingservice.service;

import java.io.IOException;

import com.enit.servingservice.entity.Ad;
import com.enit.servingservice.entity.Recommandation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumer {
	@Autowired
	AdsService adsService;

//	@Autowired
//	UserStatusRepository userStatusRepository;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	int i = 0;

	@KafkaListener(topics = "recommandation", groupId = "group_id")
	public void consumeRequest(@Payload String recString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
//		StringBuilder sb = new StringBuilder(recString);
//		recString = sb.substring(0, sb.indexOf("}"));
//		recString = recString + ",\"recommandationId\":" + i + "}";
		System.out.println("recString: "+recString);
		Recommandation rec = mapper.readValue(recString, Recommandation.class);
		rec.getAd().setRecommandationId(Integer.toString(i));
		i++;
		rec.getAd().setRequestId(rec.getRequest_id());
		rec.getAd().setRecommandationId(rec.getRecommandation_id());
		adsService.saveAd(rec.getAd());

		System.out.println("Recommandation saved in memory");

//if the ad is deleted from the µRecommandation's memory, it must be deleted from the µserving's memory.
//we send a request to an endpoint in the µserving whom it's job to delete an ad from µserving's memory.

	}

//	@KafkaListener(topics = "login-logout", groupId = "group_id")
//	public void loginLogout(@Payload String userStatus) throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		StringBuilder sb = new StringBuilder(userStatus);
//
//		String userStatusStringFormat = "{" + sb.substring(sb.indexOf(",") + 1);
//		String typeEvent = sb.substring(sb.indexOf(":") + 2, sb.indexOf(",") - 1);
//		String usernameStringFormat = sb.substring(sb.lastIndexOf(":") + 2, sb.length() - 2);
//		System.out.println("usernameStringFormat: " + usernameStringFormat);
//		if (typeEvent.compareTo("login") == 0) {
//			userStatusRepository.save(new LoggedInUsers(userStatusStringFormat));
//			System.out.println("User loggin in");
//		} else if (typeEvent.compareTo("logout") == 0) {
//			userStatusRepository.deleteById(userStatusStringFormat);
//			System.out.println("User logged out");
//		}
//
//	}
}
