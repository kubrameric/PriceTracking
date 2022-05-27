package com.bitirme.productservice.controller;

import com.bitirme.productservice.model.dto.ProductRequest;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import com.bitirme.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/createProduct")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Product createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getProduct")
    public Optional<Product> getProduct(@RequestParam("productId") String productId) {
        return productService.getProduct(productId);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping ("/getProductByStatus")
    public List<Product> getProducts(@RequestParam("productStatus")ProductStatus productStatus,
                                     @RequestParam("username") String username) {
        return productService.getProducts(productStatus,username);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestParam("productId") String productId,
                                 @Valid @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/deleteProduct")
    public Optional<Product> deleteProduct(@RequestBody ProductRequest productRequest) {
        return productService.removeProduct(productRequest.getUrl());
    }
}
