package com.bitirme.productservice.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ScraperEventDto {
    String url;
    String marketPlace;
    long id;
}
