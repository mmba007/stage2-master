//package com.enit.authentication.config;
//import com.enit.authentication.events.Event;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//
//
//
//
//@Configuration
//
//public class KafkaConfiguration {
//
//    @Value("${KAFKA}")
//    private String kafkaUrl;
//    @Bean
//  public ProducerFactory producerFactory()
//  {   Map<String,Object> config=new HashMap<>();
//      config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:19092");
//      config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//      config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//
//    return new DefaultKafkaProducerFactory(config);
//  }
//@Bean
//public KafkaTemplate<String, Event> kafkaTemplate()
//{
//	return new KafkaTemplate<>(producerFactory());
//}
//}
