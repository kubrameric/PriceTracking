package com.bitirme.productservice.kafka.consumer.config;

import com.bitirme.productservice.model.dto.ProductPriceDto;
import com.bitirme.productservice.model.dto.ScraperEventDto;
import com.bitirme.productservice.model.dto.UserDto;
import org.apache.catalina.User;
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
    public ConsumerFactory<String, ProductPriceDto> consumerFactory(
            @Qualifier("kafkaConsumerConfigurationProperties") KafkaConsumerConfigurationProperties kafkaConsumerConfigurationProperties){

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaConsumerConfigurationProperties.getBootstrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerConfigurationProperties.getGroupId());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigurationProperties.getKeySerializer());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigurationProperties.getValueSerializer());

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(ProductPriceDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductPriceDto> consumerListenerFactory(
            ConsumerFactory<String,ProductPriceDto> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, ProductPriceDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }


}
