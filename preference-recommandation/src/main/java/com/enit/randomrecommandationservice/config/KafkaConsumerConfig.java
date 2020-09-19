//package com.enit.randomrecommandationservice.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.enit.randomrecommandationservice.entity.ListRecommandation;
//import com.enit.randomrecommandationservice.entity.Recommandation;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//@Configuration
//public class KafkaConsumerConfig {
//	@Bean
//	public Map<String, Object> consumerConfig() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:19092");
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id2");
//		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,"5000000");
//		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,"2");
//		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"30000");
//		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,"25000");
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//     	props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//
////		props.put("auto.offset.reset", "earliest");
//		return props;
//	}
//
//	@Bean
//	public ConsumerFactory<String, String> consumerFactory() {
//		return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new StringDeserializer());
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory());
//
//		return factory;
//	}
//	@Bean
//	public ProducerFactory producerFactory() {
//		Map<String, Object> config = new HashMap<>();
//		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.100:19092");
//		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//		return new DefaultKafkaProducerFactory(config);
//	}
//
//	@Bean
//	public KafkaTemplate<String, ListRecommandation> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//
//}
