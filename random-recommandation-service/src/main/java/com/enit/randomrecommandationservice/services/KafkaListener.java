package com.enit.randomrecommandationservice.services;


import com.enit.randomrecommandationservice.config.EventService;
import com.enit.randomrecommandationservice.config.MyStream;
import com.enit.randomrecommandationservice.entity.Ad;
import com.enit.randomrecommandationservice.entity.ListRecommandation;
import com.enit.randomrecommandationservice.entity.Recommandation;
import com.enit.randomrecommandationservice.entity.Request;
import com.enit.randomrecommandationservice.events.DeleteAdEvent;
import com.enit.randomrecommandationservice.events.Event;
import com.enit.randomrecommandationservice.events.SaveAdEvent;
import com.enit.randomrecommandationservice.events.UpdateAdEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        List<Ad> listAds1 = adsService.findByLocationNear(new Point(request.getLar(),request.getLon()),new Distance(100	, Metrics.MILES));
        System.out.println("There is " + listAds1.size() + "  listAds1");
        System.out.println(request);

        if(listAds1.size()>0) {// This is why we called this microservice:RandomRecommandation

            List<Recommandation> listRec=listAds1.stream().map(ads -> new Recommandation(ads)).collect(Collectors.toList());

            kafkaTemplate.sendUserRecommandation(new ListRecommandation(listRec,user_id));




        }
    }

    }
