package services;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import entity.Ad;
import entity.Status;
import events.SaveAdEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import repository.UserProfileRepository;

@Service
public class KafkaConsumer {
	@Autowired
	AdsService adsService;
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
//	@Autowired
//	UserStatusRepository userStatusRepo;

	@Autowired
	UserProfileRepository userProfileRepo;

	@Autowired
	private Gson gson;

	@KafkaListener(topicPartitions = {
			@TopicPartition(topic = "adsToBeConsumed", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) }, groupId = "group_id2")
	public void consume(@Payload String message) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		SaveAdEvent event2= objectMapper.readValue(message,SaveAdEvent.class);
		adsService.saveAd(new Ad(event2.getCategory(), event2.getTitle(),event2.getDescription(),event2.getPrice(), event2.getAdvertiserPhoneNumber(),event2.getCountry(),event2.getState(),event2.getCity(),event2.getStatus(),event2.getAdImagesDirectory(),event2.getCondition(),event2.getModel(),event2.getBrand(),event2.getViews(),event2.getRate(),event2.getLocation()));
		System.out.println(event2.getType());
//		tring category, String title, String description, int price, int advertiserPhoneNumber, String country, String state, String city, Status
//		status, String adImagesDirectory, String condition, String model, String brand, int views, float rate, double[] location

//		String adStringFormat = "{" + sb.substring(sb.indexOf(",") + 1);
//		String typeEvent = sb.substring(sb.indexOf(":") + 2, sb.indexOf(",") - 1);
//		Ad ad = mapper.readValue(adStringFormat, Ad.class);
//		if (typeEvent.compareTo("ad saved") == 0) {
//			adsService.saveAd(ad);
//			System.out.println("Ad saved in memory :" + ad.toString());
//		} else if (typeEvent.compareTo("ad updated") == 0) {
//			adsService.saveAd(ad);
//			System.out.println("Ad updated and saved in memory :" + ad.toString());
//		} else if (typeEvent.compareTo("ad deleted") == 0) {
//			if (!adsService.findById(ad.getId()).isEmpty()) {
//				adsService.deleteAd(ad.getId());
//				System.out.println("Ad deleted from memory :" + ad.toString());
//			}
//		}

///////////////////If the ad is deleted, what do we do? ***************************************************

//		if (typeEvent.compareTo("ad deleted") == 0) {
//			adsService._______;
//		}

	}

//	@KafkaListener(topicPartitions = {
//			@TopicPartition(topic = "login-logout", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) }, groupId = "group_id1")
//	public void loginLogout(@Payload String userStatus) throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		StringBuilder sb = new StringBuilder(userStatus);
//
//		String userStatusStringFormat = "{" + sb.substring(sb.indexOf(",") + 1);
//		String typeEvent = sb.substring(sb.indexOf(":") + 2, sb.indexOf(",") - 1);
//		String usernameStringFormat = sb.substring(sb.lastIndexOf(":") + 2, sb.length() - 2);
//		System.out.println("usernameStringFormat: " + usernameStringFormat);
//		if (typeEvent.compareTo("login") == 0) {
//			userStatusRepo.save(new LoggedInUsers(usernameStringFormat));
//
////			List<String> preferences = new RestTemplate().getForObject(
////					"http://localhost:8099/api/user/consumerPreferencesByUsername/" + usernameStringFormat, List.class);
////			userProfileRepo.save(new UserProfile(usernameStringFormat, preferences));
//
//			System.out.println("User loggin in");
//		} else if (typeEvent.compareTo("logout") == 0) {
//			if (!userStatusRepo.findById(usernameStringFormat).isEmpty()) {
//				userStatusRepo.deleteById(usernameStringFormat);
//				System.out.println("User logged out");
//			}
//
//		}
//
//	}
//
//	@KafkaListener(topics = "usersProfiles", groupId = "group_id1")
//	public void userPreferences(@Payload String userProfile)
//			throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		StringBuilder sb = new StringBuilder(userProfile);
//
//		String userProfileStringFormat = "{" + sb.substring(sb.indexOf(",") + 1);
//		String typeEvent = sb.substring(sb.indexOf(":") + 2, sb.indexOf(",") - 1);
//		UserProfile profile = mapper.readValue(userProfileStringFormat, UserProfile.class);
//
//		System.out.println("User Profile: " + profile);
//		if (typeEvent.compareTo("user preferences updated") == 0) {
//			userProfileRepo.save(profile);
//			System.out.println("User profile updated");
//		} else if (typeEvent.compareTo("user account deleted") == 0) {
//			if (!userProfileRepo.findById(profile.getUsername()).isEmpty()) {
//				userProfileRepo.deleteById(profile.getUsername());
//				System.out.println("User account deleted");
//			}
//			if (!userStatusRepo.findById(profile.getUsername()).isEmpty()) {
//				userStatusRepo.deleteById(profile.getUsername());
//				System.out.println("User logged out by deleting his account");
//			}
//
//		}
//	}
//
////this listener needs to start from beginning. Otherwise if we restart the µservice we must save new ads again.
//	@KafkaListener(topics = "requestRecommandation", groupId = "group_id1")
//	public void consumeRequest(@Payload String requestString)
//			throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper = new ObjectMapper();
//
//		StringBuilder sb = new StringBuilder(requestString);
//		String user_idJson = "{" + sb.substring(sb.indexOf(",") + 1);
//		String user_id = user_idJson.substring(user_idJson.indexOf(":") + 2, user_idJson.indexOf("}") - 1);
//		String requestId = sb.substring(sb.indexOf(":") + 2, sb.indexOf(",") - 1);
//		List<Ad> listAds = adsService.getAllAds();
//		System.out.println("Request to be saved is : userId: " + user_id + " & requestId: " + requestId);
//		// System.out.println("ahla");
//		System.out.println("There is " + listAds.size() + " ads saved in memory");
////		System.out.println("*****ad to be send to recommandation topic is :");
////		Ad someAd = listAds.get(0);
////		System.out.println(someAd.toString());
//
//		// This is why we called this microservice:RandomRecommandation
//		Random ran = new Random();
//
//		int nxt;
//		Ad ad;
//		Object[] listAdsArray = listAds.toArray();
//		for (int i = 0; i < 6; i++) {
//			nxt = ran.nextInt(listAds.size());
//
//			ad = (Ad) listAdsArray[nxt];
//			ad.setRequestId(requestId);
////				ad =new Gson().fromJson(ad.toString(), Ad.class);
//			// Converting the Object to JSONString
//			String adString = mapper.writeValueAsString(ad);
//			kafkaTemplate.send("recommandation", adString);
////				kafkaTemplate.send("recommandation",
////						ad.toString().substring(0, ad.toString().indexOf("}")) + ",\"requestId\":" + requestId + "}");
//
//		}
//
//	}
}
