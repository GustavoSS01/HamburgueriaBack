package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
}
