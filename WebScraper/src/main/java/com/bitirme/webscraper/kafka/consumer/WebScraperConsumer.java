package com.bitirme.webscraper.kafka.consumer;

import com.bitirme.webscraper.kafka.producer.MessageService;
import com.bitirme.webscraper.model.ProductDto;
import com.bitirme.webscraper.model.ScraperEventDto;
import com.bitirme.webscraper.service.WebScraperService;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WebScraperConsumer {
    private final WebScraperService webScraperService;

    private final MessageService messageService;

    @KafkaListener(topicPattern = "^(.*-cluster.)?market-topic", containerFactory = "consumerListenerFactory")
    public void consume(ScraperEventDto event) {
        if(isEventEmpty(event)) {
            System.out.println("event is null");
            return;
        }
        BigDecimal price = BigDecimal.valueOf(0);
        String marketPlace= event.getMarketPlace();
        String url = event.getUrl();

        if(marketPlace.equals("GITTIGIDIYOR")){
             price = webScraperService.gittigidiyorScraper(url);
        }else if(marketPlace.equals("HEPSIBURADA")){
             price = webScraperService.hepsiburadaScraper(url);
        }else{
             price = webScraperService.trendyolScraper(url);
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(event.getId());
        productDto.setPrice(price);
        messageService.send(productDto);
    }

    private boolean isEventEmpty(ScraperEventDto event) {
        return event == null || event.getUrl() == null ||event.getMarketPlace() == null ;
    }
}
