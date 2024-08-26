package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }
}
