package com.bitirme.productservice.kafka.producer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaProducerConfiguration {

    @Bean("kafkaProducerConfigurationProperties")
    @ConfigurationProperties(prefix = "kafka.producer")
    public KafkaProducerConfigurationProperties kafkaProducerConfigurationProperties() {
        return new KafkaProducerConfigurationProperties();
    }
    @Bean(name = "kafkaProducer")
    public KafkaProducer<String, String> createProducer(@Qualifier (value = "kafkaProducerConfigurationProperties")
                                                            KafkaProducerConfigurationProperties kafkaProducerConfigurationProperties){
        Properties properties = new Properties();
        properties.put(ProducerConfig.ACKS_CONFIG, kafkaProducerConfigurationProperties.getAcks());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerConfigurationProperties.getBootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigurationProperties.getKeySerializer());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigurationProperties.getValueSerializer());

        return new KafkaProducer<>(properties);

    }
}
