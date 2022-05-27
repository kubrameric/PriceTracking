package com.bitirme.productservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NotificationEventDto {
    String url;
    BigDecimal price;
    String userEmail;
}
