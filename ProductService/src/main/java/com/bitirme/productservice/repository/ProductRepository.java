package com.bitirme.productservice.repository;

import com.bitirme.productservice.model.entity.Product;
import com.bitirme.productservice.model.enumeration.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> getProductById(Long productId);
    Optional<Product> removeProductById(Long productId);
    List<Product> getAllByProductStatus(ProductStatus productStatus);

    Optional<Product> getProductByUrl(String url);
    Product getProductByUsername(String username);

    List<Product> getAllByProductStatusAndUsername(ProductStatus productStatus, String username);
}
