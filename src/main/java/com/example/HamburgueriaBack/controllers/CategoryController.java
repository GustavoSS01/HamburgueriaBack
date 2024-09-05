package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.CategoryDto;
import com.example.HamburgueriaBack.models.CategoryModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.HamburgueriaBack.services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if(categoryService.existsByName(categoryDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: name already in use!");
        }
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<CategoryModel> productModelOptional = categoryService.findById(id);
        return productModelOptional.<ResponseEntity<Object>>map(productModel -> ResponseEntity.status(HttpStatus.OK).body(productModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"));
    }
}