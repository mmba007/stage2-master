package com.enit.randomrecommandationservice.services;


import com.enit.randomrecommandationservice.config.EventService;
import com.enit.randomrecommandationservice.config.MyStream;
import com.enit.randomrecommandationservice.entity.*;
import com.enit.randomrecommandationservice.events.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class KafkaListener {
    @Autowired
    AdsService adsService;

    @Autowired
    UserService userService;

    private final EventService kafkaTemplate;
    public KafkaListener(EventService eventService){
        this.kafkaTemplate=eventService;
    }

    @StreamListener(MyStream.INPUT_EVENT)
    public void handleLoginRequest(@Payload String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();


        Event rootNode = objectMapper.readValue(message, SaveAdEvent.class);
        switch(rootNode.getType()){
            case CREATE_AD:

                SaveAdEvent event2= objectMapper.readValue(message,SaveAdEvent.class);
                adsService.saveAd(new Ad(event2.getId(),event2.getCategory(), event2.getTitle(),event2.getDescription(),event2.getPrice(), event2.getAdvertiserPhoneNumber(),event2.getCountry(),event2.getState(),event2.getCity(),event2.getStatus(),event2.getAdImagesDirectory(),event2.getCondition(),event2.getModel(),event2.getBrand(),event2.getViews(),event2.getRate(),event2.getLocation().values().toArray(new Double[0])));
                System.out.println(event2.getType());
                break;
            case DELETE_AD:
                DeleteAdEvent event= objectMapper.readValue(message, DeleteAdEvent.class);
                //adsService.saveAd(new Ad(event.getCategory(), event.getTitle(),event.getDescription(),event.getPrice(), event.getAdvertiserPhoneNumber(),event.getCountry(),event.getState(),event.getCity(),event.getStatus(),event.getAdImagesDirectory(),event.getCondition(),event.getModel(),event.getBrand(),event.getViews(),event.getRate(),event.getLocation().values().toArray(new Double[0])));
                adsService.deleteAd(event.getId());
                System.out.println(event.getType());
                break;
            case UPDATE_AD:
                UpdateAdEvent adEvent= objectMapper.readValue(message,UpdateAdEvent.class);
                adsService.saveAd(new Ad(adEvent.getId(),adEvent.getCategory(), adEvent.getTitle(),adEvent.getDescription(),adEvent.getPrice(), adEvent.getAdvertiserPhoneNumber(),adEvent.getCountry(),adEvent.getState(),adEvent.getCity(),adEvent.getStatus(),adEvent.getAdImagesDirectory(),adEvent.getCondition(),adEvent.getModel(),adEvent.getBrand(),adEvent.getViews(),adEvent.getRate(),adEvent.getLocation().values().toArray(new Double[0])));
                System.out.println(adEvent.getType()+"hello");
                break;
    }}

    @StreamListener(MyStream.INPUT_REQUEST)
    public void handleUserRecommandation(@Payload String requestString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Request request = mapper.readValue(requestString, Request.class);
        String user_id = request.getUsername();
        List<Ad> listAds1 = adsService.findByLocationAndPreferences(100,request.getPreferences(),request.getLar(),request.getLon());
//        System.out.println("There is " + listAds1.size() + "  listAds1");
//        System.out.println(request);
          System.out.println(user_id);
        if(listAds1.size()>0) {// This is why we called this microservice:RandomRecommandation

            List<Recommandation> listRec=listAds1.stream().map(ads -> new Recommandation(ads)).collect(Collectors.toList());
             ListRecommandation rec=new ListRecommandation(listRec,user_id);
           System.out.println(rec);
            kafkaTemplate.sendUserRecommandation(rec);




        }
    }

    @StreamListener(MyStream.INPUT_USER_EVENT)
    public void handleUserEvent(@Payload String message) throws JsonProcessingException {

        ObjectMapper objectMapper=new ObjectMapper();
        final ObjectNode node = new ObjectMapper().readValue(message, ObjectNode.class);
        EventName eventType=EventName.valueOf(node.get("type").asText());
        switch( eventType ){
            case REGISTER_USER:

                RegisterUserEvent event2= objectMapper.readValue(message,RegisterUserEvent.class);
                userService.saveUser(new User(event2.getUsername()) );
                break;
            case DELETE_USER:
                DeleteUserEvent event= objectMapper.readValue(message, DeleteUserEvent.class);
                //adsService.saveAd(new Ad(event.getCategory(), event.getTitle(),event.getDescription(),event.getPrice(), event.getAdvertiserPhoneNumber(),event.getCountry(),event.getState(),event.getCity(),event.getStatus(),event.getAdImagesDirectory(),event.getCondition(),event.getModel(),event.getBrand(),event.getViews(),event.getRate(),event.getLocation().values().toArray(new Double[0])));
                userService.deleteUser(event.getUsername());
                System.out.println(event.getType());
                break;
            case UPDATE_USER:
                UpdateUserEvent event3= objectMapper.readValue(message,UpdateUserEvent.class);
                    System.out.println(event3.getUsername());
                    userService.saveUser(new User(event3.getUsername(),event3.getPreferences(),event3.getImpPreferences()) );


        }





        }
    }


