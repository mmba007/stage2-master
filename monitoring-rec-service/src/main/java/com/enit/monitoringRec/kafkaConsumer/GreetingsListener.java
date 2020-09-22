package com.enit.monitoringRec.kafkaConsumer;


import com.enit.monitoringRec.configuration.EventService;
import com.enit.monitoringRec.configuration.MyStream;
import com.enit.monitoringRec.entity.ConsumerRequest;
import com.enit.monitoringRec.entity.ListRecommandation;
import com.enit.monitoringRec.events.Event;
import com.enit.monitoringRec.events.LogInUserEvent;
import com.enit.monitoringRec.repository.RedisAdRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class GreetingsListener {
    @Autowired
    RedisAdRepository adsService;

    ObjectMapper mapper = new ObjectMapper();

    private final EventService kafkaTemplate;
    public  GreetingsListener(EventService eventService){
        this.kafkaTemplate=eventService;
    }
    @StreamListener(MyStream.INPUT_LOGIN)
    public void handleLoginRequest(@Payload String login) throws JsonProcessingException {



        System.out.println("\n************** login string is: "+login+"  ************************\n");
        LogInUserEvent loginEvent = mapper.readValue(login, LogInUserEvent.class);
        ConsumerRequest consumerRequest = new ConsumerRequest(loginEvent.getUsername(), loginEvent.getLatitude(), loginEvent.getLongitude());
        adsService.delete(consumerRequest.getUsername());
        System.out.println("\n hello 1");
        kafkaTemplate.sendUserRequest( consumerRequest);
        System.out.println("\n hello 2");
    }


    @StreamListener(MyStream.INPUT_RECOMMANDATION)
    public void handleUserRecommandation(@Payload String recommandations) throws JsonProcessingException {

        System.out.println("\n ***** Input List of recommandations: "+recommandations);
        ListRecommandation list = mapper.readValue(recommandations, ListRecommandation.class);
        list.getListRecommandation().forEach(rec -> {
            System.out.println("\n ***** before save   ******");
            adsService.save(list.getUsername(), rec.getAd().getId(),rec.getAd());
            System.out.println("\n ***** after save   ******");
          });


        System.out.println("\n ***** Recommandations saved in memory");

    }
}
