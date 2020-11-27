//package com.enit.usercrud.service;
//
//import com.enit.usercrud.events.Event;
//import com.enit.usercrud.events.EventWrapper;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaConsumerOneConfig {
//
//@Bean
//public ConsumerFactory<String, String> consumerFactory(){
////    JsonDeserializer<String> deserializer = new JsonDeserializer<>(EventWrapper.class);
////    deserializer.setRemoveTypeHeaders(false);
////    deserializer.addTrustedPackages("*");
////    deserializer.setUseTypeMapperForKey(true);
//
//    Map<String, Object> config = new HashMap<>();
//
//    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:19092");
//    config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_one");
//    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new StringDeserializer());
//}
//
//@Bean
//public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
//    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//    factory.setConsumerFactory(consumerFactory());
//    return factory;
//
//}
//}