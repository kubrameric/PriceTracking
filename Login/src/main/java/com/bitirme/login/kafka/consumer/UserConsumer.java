package com.bitirme.login.kafka.consumer;

import com.bitirme.login.dto.ConsumeEventDto;
import com.bitirme.login.dto.UserDto;
import com.bitirme.login.kafka.producer.MessageService;
import com.bitirme.login.model.User;
import com.bitirme.login.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserConsumer {
    private final UserService userService;
    private final MessageService messageService;

    @KafkaListener(topicPattern = "^(.*-cluster.)?username-topic", containerFactory = "consumerListenerFactory")
    public void consume(ConsumeEventDto event) {
        if(isEventEmpty(event)) {
            System.out.println("event is null");
            return;
        }
        User user = userService.findByUsername(event.getUsername());
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setProductId(event.getProductId());
        messageService.send(userDto);

    }

    private boolean isEventEmpty(ConsumeEventDto event) {
        return event == null || event.getUsername() == null ;
    }
}
