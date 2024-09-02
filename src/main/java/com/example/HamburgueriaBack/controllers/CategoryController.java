package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.CategoryDto;
import com.example.HamburgueriaBack.models.CategoryModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HamburgueriaBack.services.CategoryService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {


    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
    }

}