package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.dtos.ProductDto;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Operation(summary = "Cadastrar novo produto", description = "Endpoint para cadastrar um novo produto")
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
        if(productService.existsByName(productDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um produto cadastrado com esse nome!");
        }
        if(productService.existsByDescription(productDto.getDescription())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A descrição desse produto coincide com a de outro!");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        //productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        productService.setProductCategory(productModel, productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }

    @Operation(summary = "Listar todos os produtos", description = "Endpoint para listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @Operation(summary = "Buscar um produto pelo ID", description = "Endpoint para buscar um produto pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        return productModelOptional.<ResponseEntity<Object>>map(productModel -> ResponseEntity.status(HttpStatus.OK).body(productModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"));
    }

    @Operation(summary = "Deletar um produto", description = "Endpoint para deletar um produto pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

    @Operation(summary = "Atualizar um produto", description = "Endpoint para atualizar um produto pelo seu ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductDto productDto) {

        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }

        var productModel = productModelOptional.get();

        // Atualiza apenas os campos enviados no DTO
        if (productDto.getName() != null && !productDto.getName().isEmpty()) {
            productModel.setName(productDto.getName());
        }
        if (productDto.getDescription() != null && !productDto.getDescription().isEmpty()) {
            productModel.setDescription(productDto.getDescription());
        }
        if (productDto.getImage() != null && !productDto.getImage().isEmpty()) {
            productModel.setImage(productDto.getImage());
        }

        // Atualização do relacionamento de categoria, se necessário
        if (productDto.getCategory() != null) {
            productService.setProductCategory(productModel, productDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }

}