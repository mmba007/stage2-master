//package com.enit.adscrud.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.enit.adscrud.events.Event;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//
//
//@Configuration
//public class KafkaConfiguration {
//	@Value("${BOOTSTRAP_SERVER}")
//	private String KAFKA_URL;
//
//	@Value("${BOOTSTRAP_PORT}")
//	private String KAFKA_PORT;
//
//
//	@Bean
//	public ProducerFactory producerFactory() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URL+":"+KAFKA_PORT);
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//		return new DefaultKafkaProducerFactory(config);
//	}
//
//	@Bean
//	public KafkaTemplate<String, Event> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//}
