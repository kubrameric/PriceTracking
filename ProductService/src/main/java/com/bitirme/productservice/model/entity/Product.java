package com.bitirme.productservice.model.entity;

import com.bitirme.productservice.model.entity.BaseEntity;
import com.bitirme.productservice.model.enumeration.MarketPlace;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    String name;

    String url;

    MarketPlace marketPlace;

    ProductStatus productStatus;

    BigDecimal price;

    BigDecimal maxPriceToCondition;

    BigDecimal minPriceToCondition;

    Date createDate;

    Long userId;

    String userEmail;

    String username;
}
