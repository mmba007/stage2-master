package com.enit.monitoringRec.kafkaConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.enit.monitoringRec.configuration.EventService;
import com.enit.monitoringRec.configuration.MyStream;
import com.enit.monitoringRec.entity.Algorithm;
import com.enit.monitoringRec.entity.ListRecommandation;
import com.enit.monitoringRec.entity.ShortAdInfo;
import com.enit.monitoringRec.events.AdViewedEvent;
import com.enit.monitoringRec.repository.AlgoRepo;
import com.enit.monitoringRec.repository.RedisAdRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class KafkaListener {
	@Autowired
	RedisAdRepository adsService;

	@Autowired
	AlgoRepo algoRepo;

	ObjectMapper mapper = new ObjectMapper();

	private final EventService kafkaTemplate;

	public KafkaListener(EventService eventService) {
		this.kafkaTemplate = eventService;
	}

	@StreamListener(MyStream.INPUT_VIEWED_ADS)
	public void handleViewedAds(@Payload String message) throws JsonProcessingException {

		System.out.println("\n************** viewed ad string is: " + message + "  ************************\n");
		AdViewedEvent adViewedEvent = mapper.readValue(message, AdViewedEvent.class);
		System.out.println("\n ----------------  adViewedEvent after deserialization is: " + "\n\n   " + adViewedEvent);
		ShortAdInfo savedAdInfo = adsService.findById(adViewedEvent.getAdId());
		if (savedAdInfo != null) {
			String algoId = savedAdInfo.getAlgoId();
			if (adViewedEvent.getTimeOfView().getTime() - savedAdInfo.getTimeOfRecommandation().getTime() <= 600000) {
				System.out.println("\n  ~~~~~~~~~~~ Ad with id: " + savedAdInfo.getAdId()
						+ " is viewed within 10 minutes by user: " + adViewedEvent.getUsername()
						+ " after been recommanded by: " + savedAdInfo.getAlgoId() + " algorithm.");
				// add +1 to the score of the algo
				Algorithm algo = algoRepo.findById(algoId).get();
				if (algo != null) {
					System.out.println(
							"\n ------ Algorithm " + algoId + " found in db and has score: " + algo.getScore());
					algo.incrementScore();
					algoRepo.save(algo);
					System.out.println("\n ------------ Score of Algorithm " + algoId + " incremented");
				}
			}
		} else {
			System.out.println("\n !!!!!!!!   Ad is not saved in monitoring db");
		}
//		ConsumerRequest consumerRequest = new ConsumerRequest(loginEvent.getUsername(), loginEvent.getLatitude(),
//				loginEvent.getLongitude());
//		adsService.delete(consumerRequest.getUsername());
//		System.out.println("\n hello 1");
//		kafkaTemplate.sendUserRequest(consumerRequest);
//		System.out.println("\n hello 2");
	}

	@StreamListener(MyStream.INPUT_RECOMMANDATION)
	public void handleUserRecommandation(@Payload String recommandations) throws JsonProcessingException {

		System.out.println("\n ***** Input List of recommandations: " + recommandations);
		ListRecommandation list = mapper.readValue(recommandations, ListRecommandation.class);
		System.out.println("\n ------------ ListRecommandations after deserialization is: \n\n     " + list);
		algoRepo.save(new Algorithm(list.getAlgoId()));
		System.out.println("\n ******* Algorithm " + list.getAlgoId() + " saved successfully");
		list.getListRecommandation().forEach(rec -> {
			System.out.println("\n ***** before save AD Infos ******");
			adsService.save(rec.getAd().getId(),
					new ShortAdInfo(rec.getAd().getId(), list.getAlgoId(), list.getUsername()));
			System.out.println("\n ***** after save Ad Infos  ******");
		});

		System.out.println("\n ***** Short Ads' Infos saved in memory");

	}
}
