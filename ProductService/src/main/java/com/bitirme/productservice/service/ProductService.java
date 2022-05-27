package com.bitirme.productservice.service;

import com.bitirme.productservice.model.dto.ProductRequest;
import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.model.enumeration.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(ProductRequest productRequest);
    Optional<Product> getProduct(String productId);
    Product updateProduct(String productId, ProductRequest productRequest);
    List<Product> getProducts(ProductStatus productStatus);
    List<Product> getProducts(ProductStatus productStatus,String username);
    Optional<Product> removeProduct(String productId);
}
