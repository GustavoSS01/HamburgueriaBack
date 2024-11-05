package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.dtos.ProductDto;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.repositories.CategoryRepository;
import com.example.HamburgueriaBack.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public void setProductCategory(ProductModel product, ProductDto productDto){
        product.setCategory(categoryRepository.findByName(productDto.getCategory()));
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