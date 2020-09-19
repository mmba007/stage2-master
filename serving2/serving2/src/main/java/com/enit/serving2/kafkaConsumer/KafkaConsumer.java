//package com.enit.serving2.kafkaConsumer;
//
//import java.io.IOException;
//
//import java.util.stream.Collectors;
//
//
//import com.enit.serving2.entity.ConsumerRequest;
//import com.enit.serving2.entity.ListRecommandation;
//import com.enit.serving2.events.LogInUserEvent;
//import com.enit.serving2.repository.RedisAdRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//public class KafkaConsumer {
//    @Autowired
//    RedisAdRepository adsService;
//
//
//
//    @Autowired
//    KafkaTemplate<String, ConsumerRequest> kafkaTemplate;
//
//    private int i=0;
//
//    @KafkaListener(topics = "recommandation", groupId = "group_id")
//    public void consumeRequest(@Payload String recString) throws JsonParseException, JsonMappingException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("recString: "+recString);
//        ListRecommandation list = mapper.readValue(recString, ListRecommandation.class);
//        list.getListRecommandation().forEach(rec -> {adsService.save(rec.getUsername(),Integer.toString(i),rec.getAd());i++;});
//
//
//        System.out.println("Recommandation saved in memory");
//
//    }
//
//    @KafkaListener(topics = "login-logout",groupId = "group_id")
//    @Transactional
//    public void loginLogout(@Payload String userStatus) throws JsonParseException, JsonMappingException, IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        System.out.println(userStatus);
//        LogInUserEvent loginEvent = objectMapper.readValue(userStatus, LogInUserEvent.class);
//        ConsumerRequest consumerRequest = new ConsumerRequest(loginEvent.getUsername(), loginEvent.getLatitude(), loginEvent.getLongitude());
//        adsService.delete(consumerRequest.getUsername());
//        System.out.println("hello 1");
//        kafkaTemplate.send("requestRecommandation", consumerRequest);
//        System.out.println("hello 2");
//
//            /*case :
//                LogOutUserEvent loginEvent = (LogInUserEvent) rootNode;
//                ConsumerRequest consumerRequest = new ConsumerRequest(loginEvent.getUsername(), loginEvent.getLatitude(), loginEvent.getLongitude());
//                kafkaTemplate2.send("requestRecommandation", consumerRequest);
//                break;*/
//
//
//    }
//
//
//}
