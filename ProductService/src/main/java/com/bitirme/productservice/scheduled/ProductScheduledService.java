package com.bitirme.productservice.scheduled;

import com.bitirme.productservice.kafka.producer.MessageService;
import com.bitirme.productservice.model.dto.ScraperEventDto;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import com.bitirme.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ProductScheduledService {
    private final ProductService productService;
    private final MessageService messageService;

    @Scheduled(cron = "0 */30 * ? * *")
    public void scheduledRun() throws InterruptedException {
        List<Product> productList = productService.getProducts(ProductStatus.ACTIVE);
        if(!productList.isEmpty()) {
            for(Product product:productList){
                ScraperEventDto event = new ScraperEventDto();
                event.setId(product.getId());
                event.setMarketPlace(product.getMarketPlace().toString());
                event.setUrl(product.getUrl());

                messageService.send(event,"market-topic");
                TimeUnit.MINUTES.sleep(1);

            }
        }
    }
}
