package com.bitirme.notificationservice.kafka;

import com.bitirme.notificationservice.model.EmailMessage;
import com.bitirme.notificationservice.model.dto.NotificationEventDto;
import com.bitirme.notificationservice.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationConsumer {

    private final EmailSenderService emailSenderService;


    @KafkaListener(topicPattern = "^(.*-cluster.)?notification-topic", containerFactory = "consumerListenerFactory")
    public void consumeScraper(NotificationEventDto event) {
        if(isEventEmpty(event)) {
            System.out.println("event is null");
            return;
        }
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(event.getUserEmail());
        emailMessage.setSubject("price tracking");
        String message = "The price of the product with this url - "+event.getUrl()+" has dropped to "+event.getPrice();
        emailMessage.setMessage(message);

    }

    private boolean isEventEmpty(NotificationEventDto event) {
        return event == null || event.getPrice().doubleValue() == 0 ||event.getUrl() == null || event.getUserEmail() == null;
    }



}
