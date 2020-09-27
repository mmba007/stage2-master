package com.enit.monitoringRec.kafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.enit.monitoringRec.configuration.EventService;
import com.enit.monitoringRec.configuration.MyStream;
import com.enit.monitoringRec.entity.Algorithm;
import com.enit.monitoringRec.entity.ListRecommandation;
import com.enit.monitoringRec.entity.ShortAdInfo;
import com.enit.monitoringRec.events.AdViewedEvent;
import com.enit.monitoringRec.repository.AlgorithmRepository;
import com.enit.monitoringRec.repository.RedisAdRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class KafkaListener {
	@Autowired
	RedisAdRepository adsService;

	@Autowired
	AlgorithmRepository algoRepo;

	ObjectMapper mapper = new ObjectMapper();

	private final EventService kafkaTemplate;

	public KafkaListener(EventService eventService) {
		this.kafkaTemplate = eventService;
	}

//	@Scheduled(cron = "0 15 10 15 * ?")
//	//@Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
//	public void scheduleTaskUsingCronExpression() {
//	 
//	    long now = System.currentTimeMillis() / 1000;
//	    System.out.println(
//	      "schedule tasks using cron jobs - " + now);
//	}
//	
//	// A fixedDelay task:
//
//		@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
//
//	// A fixedRate task:
//
//		@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
//
//	// A cron expression based task:
//
//		@Scheduled(cron = "${cron.expression}")

//	@Scheduled(fixedDelay = 10000,initialDelay = 5000)
	@Scheduled(fixedRate = 1200000) // each 20 minutes
	public void removeOldAdsAfterFixedRate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Date now = new Date();
		String strDate = sdf.format(now);
		if (adsService.deleteOldAds(1200000)) {
			System.out.println("\n####### old ads removed from cache at     " + strDate);
		}
	}

	@StreamListener(MyStream.INPUT_VIEWED_ADS)
	public void handleViewedAds(@Payload String message) throws JsonProcessingException {

//		System.out.println("\n************** viewed ad string is: " + message + "  ************************\n");
		AdViewedEvent adViewedEvent = mapper.readValue(message, AdViewedEvent.class);
//		System.out.println("\n ----------------  adViewedEvent after deserialization is: " + "\n\n   " + adViewedEvent);
		for (Algorithm algo : algoRepo.findAll()) {
			String algoId = algo.getAlgoId();
			ShortAdInfo savedAdInfo = adsService.findById(algoId, adViewedEvent.getAdId());
			if (savedAdInfo != null) {
				if (savedAdInfo.getUsername().equals(adViewedEvent.getUsername())) {
					if (!savedAdInfo.isViewedByUser()) {
//						String algoId = savedAdInfo.getAlgoId();
						if (adViewedEvent.getTimeOfView().getTime()
								- savedAdInfo.getTimeOfRecommandation().getTime() <= 600000) {
							System.out.println("\n  ~~~~~~~~~~~ Ad with id: " + savedAdInfo.getAdId()
									+ " is viewed within 10 minutes by user: " + adViewedEvent.getUsername()
									+ " after been recommanded by: " + savedAdInfo.getAlgoId() + " algorithm.");
							// add +1 to the score of the algo
//							Algorithm algo = algoRepo.findById(algoId).get();
//							if (algo != null) {
//							System.out.println("\n ------ old score of Algorithm " + algoId + " : " + algo.getScore());
							algo.incrementScore();
							algoRepo.save(algo);
							System.out.println("\n ------------ Score of Algorithm " + algoId
									+ " has incremented and became: " + algo.getScore());
							savedAdInfo.setIsViewedByUser(true);
							adsService.save(algoId, savedAdInfo.getAdId(), savedAdInfo);
//							System.out.println("\n ******* isViewedByUser is true");
//							}
						}
					} else {
						System.out.println("\n !!!!!!!! user has already seen this ad");
					}

				} else {
					System.out.println("\n  !!!!!! recommendation was not meant for this user!!");
				}

			} else {
				System.out.println("\n !!!!!!!!   Ad is not saved in monitoring db with algo-id: " + algoId);
			}
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

//		System.out.println("\n ***** Input List of recommandations: " + recommandations);
		ListRecommandation list = mapper.readValue(recommandations, ListRecommandation.class);
//		System.out.println("\n ------------ ListRecommandations after deserialization is: \n\n     " + list);
		algoRepo.save(new Algorithm(list.getAlgoId()));
//		System.out.println("\n ******* Algorithm " + list.getAlgoId() + " saved successfully");
		list.getListRecommandation().forEach(rec -> {
//			System.out.println("\n ***** before save AD Infos ******");
			adsService.save(list.getAlgoId(), rec.getAd().getId(),
					new ShortAdInfo(rec.getAd().getId(), list.getAlgoId(), list.getUsername()));
//			System.out.println("\n ***** after save Ad Infos  ******");
		});

		System.out.println("\n ***** Ads' Infos from " + list.getAlgoId() + " saved in memory");

	}
}
