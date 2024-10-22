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

    // Teste do caminho feliz
    @Test
    void testSaveProduct() {
        when(productRepository.save(productModel)).thenReturn(productModel);

        ProductModel savedProduct = productService.save(productModel);

        assertNotNull(savedProduct);
        assertEquals("Cheeseburger", savedProduct.getName());
        verify(productRepository, times(1)).save(productModel);
    }

    // Teste do caminho triste
    @Test
    void testSaveProductFailure() {
        when(productRepository.save(productModel)).thenThrow(new RuntimeException("Error saving product"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.save(productModel);
        });

        assertEquals("Error saving product", exception.getMessage());
        verify(productRepository, times(1)).save(productModel);
    }

    // Teste do caminho feliz
    @Test
    void testExistsByName() {
        when(productRepository.existsByName("Cheeseburger")).thenReturn(true);

        boolean exists = productService.existsByName("Cheeseburger");

        assertTrue(exists);
        verify(productRepository, times(1)).existsByName("Cheeseburger");
    }

    // Teste do caminho triste
    @Test
    void testExistsByNameFailure() {
        when(productRepository.existsByName("Cheeseburger")).thenReturn(false);

        boolean exists = productService.existsByName("Cheeseburger");

        assertFalse(exists);
        verify(productRepository, times(1)).existsByName("Cheeseburger");
    }

    // Teste do caminho feliz
    @Test
    void testExistsByDescription() {
        when(productRepository.existsByDescription("A burger with cheese.")).thenReturn(true);

        boolean exists = productService.existsByDescription("A burger with cheese.");

        assertTrue(exists);
        verify(productRepository, times(1)).existsByDescription("A burger with cheese.");
    }

    // Teste do caminho triste
    @Test
    void testExistsByDescriptionFailure() {
        when(productRepository.existsByDescription("A burger with cheese.")).thenReturn(false);

        boolean exists = productService.existsByDescription("A burger with cheese.");

        assertFalse(exists);
        verify(productRepository, times(1)).existsByDescription("A burger with cheese.");
    }

    // Teste do caminho feliz
    @Test
    void testFindAllProducts() {
        List<ProductModel> products = Collections.singletonList(productModel);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    // Teste do caminho triste
    @Test
    void testFindAllProductsEmpty() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductModel> foundProducts = productService.findAll();

        assertTrue(foundProducts.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    // Teste do caminho feliz
    @Test
    void testFindProductById() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.of(productModel));

        Optional<ProductModel> foundProduct = productService.findById(id);

        assertTrue(foundProduct.isPresent());
        assertEquals("Cheeseburger", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(id);
    }

    // Teste do caminho triste
    @Test
    void testFindProductByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ProductModel> foundProduct = productService.findById(id);

        assertFalse(foundProduct.isPresent());
        verify(productRepository, times(1)).findById(id);
    }

    // Teste do caminho feliz
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete(productModel);

        productService.delete(productModel);

        verify(productRepository, times(1)).delete(productModel);
    }

    // Teste do caminho triste
    @Test
    void testDeleteProductFailure() {
        doThrow(new RuntimeException("Error deleting product")).when(productRepository).delete(productModel);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.delete(productModel);
        });

        assertEquals("Error deleting product", exception.getMessage());
        verify(productRepository, times(1)).delete(productModel);
    }
}
