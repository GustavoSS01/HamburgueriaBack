package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.ProductDto;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.validation.Valid;



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
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

}
