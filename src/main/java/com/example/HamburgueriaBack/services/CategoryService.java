package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.CategoryModel;

import com.example.HamburgueriaBack.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryModel save(CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }
}
