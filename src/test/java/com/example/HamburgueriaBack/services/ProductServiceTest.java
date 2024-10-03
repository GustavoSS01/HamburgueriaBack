package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductModel productModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productModel = new ProductModel();
        productModel.setName("Cheeseburger");
        productModel.setDescription("A burger with cheese.");
    }

    @Test
    void testSaveProduct() {
        when(productRepository.save(productModel)).thenReturn(productModel);

        ProductModel savedProduct = productService.save(productModel);

        assertNotNull(savedProduct);
        assertEquals("Cheeseburger", savedProduct.getName());
        verify(productRepository, times(1)).save(productModel);
    }
    @Test
    void testExistsByName() {
        when(productRepository.existsByName("Cheeseburger")).thenReturn(true);

        boolean exists = productService.existsByName("Cheeseburger");

        assertTrue(exists);
        verify(productRepository, times(1)).existsByName("Cheeseburger");
    }

    @Test
    void testExistsByDescription() {
        when(productRepository.existsByDescription("A burger with cheese.")).thenReturn(true);

        boolean exists = productService.existsByDescription("A burger with cheese.");

        assertTrue(exists);
        verify(productRepository, times(1)).existsByDescription("A burger with cheese.");
    }

    @Test
    void testFindAllProducts() {
        List<ProductModel> products = Collections.singletonList(productModel);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.of(productModel));

        Optional<ProductModel> foundProduct = productService.findById(id);

        assertTrue(foundProduct.isPresent());
        assertEquals("Cheeseburger", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(productModel);

        productService.delete(productModel);

        verify(productRepository, times(1)).delete(productModel);
    }
}