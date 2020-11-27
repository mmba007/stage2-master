package com.enit.usercrud.service;



import com.enit.usercrud.config.EventService;
import com.enit.usercrud.config.MyStream;
import com.enit.usercrud.events.DeleteUserEvent;
import com.enit.usercrud.events.Event;
import com.enit.usercrud.events.RegisterUserEvent;
import com.enit.usercrud.events.UpdateUserEvent;
import com.enit.usercrud.model.EventName;
import com.enit.usercrud.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class KafkaListener {

    @Autowired
    UserService userService;

    @Autowired
    EventService KafkaTemplate;

    private final EventService kafkaTemplate;
    public KafkaListener(EventService eventService){
        this.kafkaTemplate=eventService;
    }

    @StreamListener(MyStream.INPUT_USER_EVENT)
    public void handleLoginRequest(@Payload String message) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        final ObjectNode node = new ObjectMapper().readValue(message, ObjectNode.class);

        EventName eventType=EventName.valueOf(node.get("type").asText());
        System.out.println(eventType);
        switch( eventType ){
            case REGISTER_USER:
                RegisterUserEvent event2= objectMapper.readValue(message,RegisterUserEvent.class);
                userService.saveUser(new User(event2.getUsername()) );
                kafkaTemplate.sendUserPreferences(event2);
                break;
            case DELETE_USER:
                DeleteUserEvent event= objectMapper.readValue(message, DeleteUserEvent.class);
                //adsService.saveAd(new Ad(event.getCategory(), event.getTitle(),event.getDescription(),event.getPrice(), event.getAdvertiserPhoneNumber(),event.getCountry(),event.getState(),event.getCity(),event.getStatus(),event.getAdImagesDirectory(),event.getCondition(),event.getModel(),event.getBrand(),event.getViews(),event.getRate(),event.getLocation().values().toArray(new Double[0])));
                userService.deleteByUsername(event.getUsername());
                kafkaTemplate.sendUserPreferences(new DeleteUserEvent(event.getUsername()));
                System.out.println(event.getType());
                break;

    }
    }


    }


