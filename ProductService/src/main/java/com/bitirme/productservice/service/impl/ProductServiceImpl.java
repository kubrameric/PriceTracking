package com.bitirme.productservice.service.impl;

import com.bitirme.productservice.kafka.producer.MessageService;
import com.bitirme.productservice.model.dto.ProductRequest;
import com.bitirme.productservice.model.dto.ScraperEventDto;
import com.bitirme.productservice.model.dto.UserDto;
import com.bitirme.productservice.model.dto.UserEventDto;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import com.bitirme.productservice.repository.ProductRepository;
import com.bitirme.productservice.service.ProductService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final MessageService messageService;

    public ProductServiceImpl(ProductRepository productRepository, MessageService messageService) {
        this.productRepository = productRepository;
        this.messageService = messageService;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .url(productRequest.getUrl())
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .minPriceToCondition(productRequest.getMinPriceToCondition())
                .maxPriceToCondition(productRequest.getMaxPriceToCondition())
                .marketPlace(productRequest.getMarketPlace())
                .productStatus(productRequest.getProductStatus())
                .createDate(new Date())
                .username(productRequest.getUsername())
                .build();
        productRepository.save(product);

        UserEventDto userEventDto = new UserEventDto();
        userEventDto.setUsername(productRequest.getUsername());
        userEventDto.setProductId(product.getId());
        messageService.send(userEventDto,"username-topic");

        ScraperEventDto event = new ScraperEventDto();
        event.setMarketPlace(product.getMarketPlace().toString());
        event.setUrl(product.getUrl());
        event.setId(product.getId());
        messageService.send(event,"market-topic");
        return null;
    }

    @Override
    public Optional<Product> getProduct(String productId) {
        Long id = Long.parseLong(productId);
        return productRepository.getProductById(id);
    }

    @Override
    public Product updateProduct(String productId, ProductRequest productRequest) {
        Product product = getProduct(productId).get();
        product.setProductStatus(productRequest.getProductStatus());
        product.setUrl(productRequest.getUrl());
        product.setCreateDate(productRequest.getCreateDate());
        product.setMarketPlace(productRequest.getMarketPlace());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setMaxPriceToCondition(productRequest.getMaxPriceToCondition());
        product.setMinPriceToCondition(productRequest.getMinPriceToCondition());
        product.setUserEmail(productRequest.getUserMail());
        product.setUserId(productRequest.getUserId());
        product.setUsername(productRequest.getUsername());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts(ProductStatus productStatus) {
        return productRepository.getAllByProductStatus(productStatus);
    }
    @Override
    public List<Product> getProducts(ProductStatus productStatus, String username) {
        //return productRepository.getAllByProductStatus(productStatus);
        return productRepository.getAllByProductStatusAndUsername(productStatus,username);
    }

    @Override
    public Optional<Product> removeProduct(String productUrl) {
        Product product = productRepository.getProductByUrl(productUrl).get();
        ProductRequest productRequest = ProductRequest.builder()
                .userId(product.getUserId())
                .userMail(product.getUserEmail())
                .createDate(product.getCreateDate())
                .marketPlace(product.getMarketPlace())
                .url(product.getUrl())
                .maxPriceToCondition(product.getMaxPriceToCondition())
                .minPriceToCondition(product.getMinPriceToCondition())
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(ProductStatus.PASSIVE)
                .username(product.getUsername())
                .build();
        return Optional.ofNullable(updateProduct(String.valueOf(product.getId()), productRequest));
    }
}
