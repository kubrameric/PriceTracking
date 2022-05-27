package com.bitirme.notificationservice.kafka.config;

import com.bitirme.notificationservice.model.dto.NotificationEventDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {
    @Bean("kafkaConsumerConfigurationProperties")
    @ConfigurationProperties(prefix = "kafka.consumer")
    public KafkaConsumerConfigurationProperties kafkaConsumerConfigurationProperties() {
        return new KafkaConsumerConfigurationProperties();
    }

    @Bean(name = "kafkaConsumer")
    public ConsumerFactory<String, NotificationEventDto> consumerFactory(
            @Qualifier("kafkaConsumerConfigurationProperties") KafkaConsumerConfigurationProperties kafkaConsumerConfigurationProperties){

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaConsumerConfigurationProperties.getBootstrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerConfigurationProperties.getGroupId());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigurationProperties.getKeySerializer());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigurationProperties.getValueSerializer());

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(NotificationEventDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationEventDto> consumerListenerFactory(ConsumerFactory<String,NotificationEventDto> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, NotificationEventDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }


}
