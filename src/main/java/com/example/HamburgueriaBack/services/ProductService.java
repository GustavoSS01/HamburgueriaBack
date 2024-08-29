package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public boolean existsByDescription(String description) {
        return productRepository.existsByDescription(description);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(ProductModel productModel) {
        productRepository.delete(productModel);
    }
}
