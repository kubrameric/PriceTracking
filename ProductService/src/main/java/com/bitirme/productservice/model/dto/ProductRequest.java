package com.bitirme.productservice.model.dto;

import com.bitirme.productservice.model.enumeration.MarketPlace;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Builder
@Getter
@Setter
public class ProductRequest {

    String username;

    String name;

    String url;

    MarketPlace marketPlace;

    ProductStatus productStatus;

    BigDecimal price;

    BigDecimal maxPriceToCondition;

    BigDecimal minPriceToCondition;

    Date createDate;

    String userMail;

    long userId;
}
