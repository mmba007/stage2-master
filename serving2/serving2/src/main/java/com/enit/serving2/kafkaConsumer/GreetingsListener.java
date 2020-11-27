package com.enit.serving2.kafkaConsumer;


import com.enit.serving2.configuration.EventService;

import com.enit.serving2.configuration.MyStream;
import com.enit.serving2.entity.ConsumerRequest;
import com.enit.serving2.entity.ListRecommandation;
import com.enit.serving2.events.Event;
import com.enit.serving2.events.LogInUserEvent;
import com.enit.serving2.repository.RedisAdRepository;
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



        System.out.println(login);
        LogInUserEvent loginEvent = mapper.readValue(login, LogInUserEvent.class);
        ConsumerRequest consumerRequest = new ConsumerRequest(loginEvent.getUsername(), loginEvent.getLatitude(), loginEvent.getLongitude());
        adsService.delete(consumerRequest.getUsername());
        System.out.println("hello 1");
        kafkaTemplate.sendUserRequest( consumerRequest);
        System.out.println("hello 2");
    }


    @StreamListener(MyStream.INPUT_RECOMMANDATION)
    public void handleUserRecommandation(@Payload String recommandations) throws JsonProcessingException {

        System.out.println("recString: "+recommandations);
        ListRecommandation list = mapper.readValue(recommandations, ListRecommandation.class);
        list.getListRecommandation().forEach(rec -> {

            adsService.save(list.getUsername(), rec.getAd().getId(),rec.getAd()); });


        System.out.println("Recommandation saved in memory");

    }
}