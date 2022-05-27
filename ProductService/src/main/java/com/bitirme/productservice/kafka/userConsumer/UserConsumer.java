package com.bitirme.productservice.kafka.userConsumer;

import com.bitirme.productservice.model.dto.ProductPriceDto;
import com.bitirme.productservice.model.dto.ProductRequest;
import com.bitirme.productservice.model.dto.UserDto;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConsumer {
    private final ProductService productService;

    @KafkaListener(topicPattern = "^(.*-cluster.)?user-topic", containerFactory = "userConsumerListenerFactory")
    public void consumeScraper(UserDto event) {
        if(isEventEmpty(event)) {
            System.out.println("event is null");
            return;
        }
        Product product = productService.getProduct(String.valueOf(event.getProductId())).get();
        ProductRequest productRequest = ProductRequest.builder()
                .userId(event.getId())
                .userMail(event.getEmail())
                .createDate(product.getCreateDate())
                .marketPlace(product.getMarketPlace())
                .url(product.getUrl())
                .maxPriceToCondition(product.getMaxPriceToCondition())
                .minPriceToCondition(product.getMinPriceToCondition())
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .username(product.getUsername())
                .build();
        productService.updateProduct(String.valueOf(event.getProductId()),productRequest);

    }

    private boolean isEventEmpty(ProductPriceDto event) {
        return event == null || event.getId() == 0 ||event.getPrice() == null ;
    }
    private boolean isEventEmpty(UserDto event) {
        return event == null || event.getId() == 0 ||event.getEmail() == null ;
    }



}
