package com.bitirme.webscraper.service.impl;

import com.bitirme.webscraper.service.WebScraperService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class WebScraperServiceImpl implements WebScraperService {
    public  WebScraperServiceImpl(){

    }

    @Override
    public BigDecimal hepsiburadaScraper(String url) {
        BigDecimal price = BigDecimal.valueOf(0);
        try {
            WebClient webClient = getWebClient();
            HtmlPage page = webClient.getPage(url);

            List<?> anchors = page.getByXPath("//*[@id=\"offering-price\"]");
            HtmlSpan link = (HtmlSpan) anchors.get(0);
            String value =link.getAttribute("content");
            price = BigDecimal.valueOf(Double.parseDouble(value));
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    @Override
    public BigDecimal trendyolScraper(String url) {
        BigDecimal price = BigDecimal.valueOf(0);
        try {
            WebClient webClient = getWebClient();
            HtmlPage page = webClient.getPage(url);

            List<?> anchors = page.getByXPath("//*[@id=\"product-detail-app\"]/div/div[2]/div[1]/div[2]/div[2]/div/div/div[4]/div/div/span");
            HtmlSpan link = (HtmlSpan) anchors.get(0);
            String n = String.valueOf(link.getFirstChild());
            String[] splits = n.split(" ");
            String firstValue = splits[0];

            String value= firstValue.replace(",", ".");

            price = BigDecimal.valueOf(Double.parseDouble(value));
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    @Override
    public BigDecimal gittigidiyorScraper(String url) {
        BigDecimal price = BigDecimal.valueOf(0);
        try {
            WebClient webClient = getWebClient();
            HtmlPage page = webClient.getPage(url);

            List<?> anchors = page.getByXPath("//*[@id=\"sp-price-lowPrice\"]");
            HtmlDivision link = (HtmlDivision) anchors.get(0);
            String value = String.valueOf(link.getFirstChild());
            value=value.replace(".","");
            value=value.replace(",",".");
            price = BigDecimal.valueOf(Double.parseDouble(value));
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            webClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    private WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        return webClient;
    }
}
