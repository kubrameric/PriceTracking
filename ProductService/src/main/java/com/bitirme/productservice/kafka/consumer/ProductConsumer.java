package com.bitirme.productservice.kafka.consumer;

import com.bitirme.productservice.kafka.producer.MessageService;
import com.bitirme.productservice.model.dto.NotificationEventDto;
import com.bitirme.productservice.model.dto.ProductPriceDto;
import com.bitirme.productservice.model.dto.ProductRequest;
import com.bitirme.productservice.model.dto.UserDto;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductConsumer {
    private final ProductService productService;

    private final MessageService messageService;

    @KafkaListener(topicPattern = "^(.*-cluster.)?price-topic", containerFactory = "consumerListenerFactory")
    public void consumeScraper(ProductPriceDto event) {
        if(isEventEmpty(event)) {
            System.out.println("event is null");
            return;
        }
        BigDecimal price= event.getPrice();
        long id = event.getId();
        Optional<Product> optionalProduct = productService.getProduct(String.valueOf(id));
        Product product = optionalProduct.get();
        ProductRequest productRequest = ProductRequest.builder()
                .productStatus(product.getProductStatus())
                .createDate(product.getCreateDate())
                .price(price)
                .marketPlace(product.getMarketPlace())
                .name(product.getName())
                .maxPriceToCondition(product.getMaxPriceToCondition())
                .minPriceToCondition(product.getMinPriceToCondition())
                .url(product.getUrl())
                .build();
        if(!product.getPrice().equals(price) ){
            if( price.doubleValue() <= product.getMaxPriceToCondition().doubleValue() &&
                    price.doubleValue() >= product.getMinPriceToCondition().doubleValue()){
                if(product.getUserEmail() != null ){
                    NotificationEventDto eventDto = new NotificationEventDto();
                    eventDto.setPrice(price);
                    eventDto.setUrl(product.getUrl());
                    eventDto.setUserEmail(product.getUserEmail());
                    messageService.send(eventDto,"notification-topic");
                }

            }
            productService.updateProduct(String.valueOf(id), productRequest);
        }

    }

    private boolean isEventEmpty(ProductPriceDto event) {
        return event == null || event.getId() == 0 ||event.getPrice() == null ;
    }



}
