package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.ProductDto;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
//import java.security.SecureRandom;
//import java.time.LocalDateTime;
//import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct (@RequestBody @Valid ProductDto productDto){
        if(productService.existsByName(productDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um produto cadastrado com esse nome!");
        }
        if(productService.existsByDescription(productDto.getDescription())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A descrição desse produto coincide com a de outro!");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        //productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        return productModelOptional.<ResponseEntity<Object>>map(productModel -> ResponseEntity.status(HttpStatus.OK).body(productModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

}
