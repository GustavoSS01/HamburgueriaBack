package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.CategoryDto;
import com.example.HamburgueriaBack.models.CategoryModel;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Criar nova categoria", description = "Endpoint para criar uma nova categoria")
    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        if(categoryService.existsByName(categoryDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: name already in use!");
        }
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
    }

    @Operation(summary = "Listar todas as categorias", description = "Endpoint para listar todas as categorias")
    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @Operation(summary = "Buscar uma categoria pelo ID", description = "Endpoint para buscar uma categoria pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCategory(@PathVariable(value = "id") UUID id){
        Optional<CategoryModel> categoryModelOptional = categoryService.findById(id);
        return categoryModelOptional.<ResponseEntity<Object>>map(categoryModel -> ResponseEntity.status(HttpStatus.OK).body(categoryModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"));
    }

    @Operation(summary = "Deletar uma categoria", description = "Endpoint para deletar uma categoria pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") UUID id){
        Optional<CategoryModel> categoryModelOptional = categoryService.findById(id);
        if (!categoryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }
        categoryService.delete(categoryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
    }

    @Operation(summary = "Atualizar uma categoria", description = "Endpoint para atualizar uma categoria pelo seu ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") UUID id, @RequestBody @Valid CategoryDto categoryDto){
        Optional<CategoryModel> categoryModelOptional = categoryService.findById(id);
        if(!categoryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
        }
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        categoryModel.setIdCategory(categoryModelOptional.get().getIdCategory());
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryModel));
    }
}