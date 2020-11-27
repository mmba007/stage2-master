//package com.enit.usercrud.service;
//import com.enit.usercrud.events.*;
//import com.enit.usercrud.model.EventName;
//import com.enit.usercrud.model.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class KafkaConsumer {
//    @Autowired
//	UserService userService;
//
//	@Autowired
//	KafkaTemplate<String, String> kafkaTemplate;
//	//kafka listener
//	@KafkaListener(
//			topicPartitions = @TopicPartition(topic = "userEvent",partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "2")    ))
//	public void listenToParition(@Payload String message) throws JsonProcessingException {
//		System.out.println(message);
////		EventName event_type = message.getType();
////		if(event_type!=null)
////		{
////			switch (event_type){
////				case DELETE_USER:
////				DeleteUserEvent e = (DeleteUserEvent) message.getData();
////					ObjectMapper objectMapper = new ObjectMapper();
////					objectMapper.readValue(message,Event.class);
////					userService.deleteByUsername(e.getUsername());
////					break;m
////
////				case UPDATE_USER:
////					 UpdateUserEvent event1=(UpdateUserEvent)message.getData();
////					 userService.updateUser(event1.getUsername(),new User(event1.getUsername(), event1.getEmail(),event1.getRole(),event1.getFirstName(), event1.getLastName(),event1.getPassword()));
////					 break;
////
////				case REGISTER_USER:
////					RegisterUserEvent event2=(RegisterUserEvent)message.getData();
////					userService.saveUser(event2.getUsername(),new User(event2.getUsername(), event2.getEmail(),event2.getRole(),event2.getFirstName(), event2.getLastName(),event2.getPassword()));
////					 break;
////				default:
////					break;
////			}
//		ObjectMapper objectMapper = new ObjectMapper();
//		RegisterUserEvent event2= objectMapper.readValue(message,RegisterUserEvent.class);
//		userService.saveUser(new User(event2.getUsername(), event2.getEmail(),event2.getRole(),event2.getFirstName(), event2.getLastName(),event2.getPassword()));
//	     System.out.println(event2.getType());
//	}
//
//
////		System.out.println("Received Messasge: " + message );
//
//
//
//}
