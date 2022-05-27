package com.bitirme.webscraper.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    long id;
    BigDecimal price;
}
