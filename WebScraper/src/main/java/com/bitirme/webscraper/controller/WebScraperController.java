package com.bitirme.webscraper.controller;

import com.bitirme.webscraper.service.WebScraperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/webScraper")
public class WebScraperController {

    private final WebScraperService webScraperService;

    @PostMapping
    public BigDecimal getPrice(@RequestParam("marketPlace") String marketPlace, @RequestParam("url") String url ) {
        if(marketPlace.equals("GITTIGIDIYOR")){
            return webScraperService.gittigidiyorScraper(url);
        }else if(marketPlace.equals("HEPSIBURADA")){
            return webScraperService.hepsiburadaScraper(url);
        }else{
            return webScraperService.trendyolScraper(url);
        }

    }
}
