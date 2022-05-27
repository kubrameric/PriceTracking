package com.bitirme.webscraper.service;

import java.math.BigDecimal;

public interface WebScraperService {
     BigDecimal hepsiburadaScraper(String url);
     BigDecimal trendyolScraper(String url);
     BigDecimal gittigidiyorScraper(String url);


}
