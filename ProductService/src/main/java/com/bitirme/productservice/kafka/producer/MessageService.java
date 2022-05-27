package com.bitirme.productservice.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    String topicName = "market-topic";
    private KafkaProducer<String, String> kafkaProducer;
    private ObjectMapper objectMapper;

    public MessageService(@Qualifier( value = "kafkaProducer") KafkaProducer<String , String> kafkaProducer, ObjectMapper objectMapper){

        this.kafkaProducer = kafkaProducer;
        this.objectMapper = objectMapper;

    }
    public void send(Object message, String topicName){
        String jsonMessage;
        try{
            jsonMessage = objectMapper.writeValueAsString(message);
            getKafkaProducer().send(new ProducerRecord<>(topicName,jsonMessage));
        }catch (JsonProcessingException e){

        }
    }
    public KafkaProducer<String ,String > getKafkaProducer() {
        return kafkaProducer;
    }
}
