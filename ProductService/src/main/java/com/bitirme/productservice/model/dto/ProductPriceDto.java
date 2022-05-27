package com.bitirme.productservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPriceDto {
    long id;
    BigDecimal price;
}
