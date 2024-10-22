package com.example.HamburgueriaBack.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.HamburgueriaBack.models.CategoryModel;
import com.example.HamburgueriaBack.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryModel categoryModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryModel = new CategoryModel();
        categoryModel.setName("Beverages");
    }

    // Teste do caminho feliz
    @Test
    void testSaveCategory() {
        when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);

        CategoryModel savedCategory = categoryService.save(categoryModel);

        assertNotNull(savedCategory);
        assertEquals("Beverages", savedCategory.getName());
        verify(categoryRepository, times(1)).save(categoryModel);
    }

    // Teste do caminho triste
    @Test
    void testSaveCategoryFailure() {
        when(categoryRepository.save(categoryModel)).thenThrow(new RuntimeException("Error saving category"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.save(categoryModel);
        });

        assertEquals("Error saving category", exception.getMessage());
        verify(categoryRepository, times(1)).save(categoryModel);
    }

    // Teste do caminho feliz
    @Test
    void testExistsByName() {
        when(categoryRepository.existsByName("Beverages")).thenReturn(true);

        boolean exists = categoryService.existsByName("Beverages");

        assertTrue(exists);
        verify(categoryRepository, times(1)).existsByName("Beverages");
    }

    // Teste do caminho triste
    @Test
    void testExistsByNameFailure() {
        when(categoryRepository.existsByName("Beverages")).thenReturn(false);

        boolean exists = categoryService.existsByName("Beverages");

        assertFalse(exists);
        verify(categoryRepository, times(1)).existsByName("Beverages");
    }

    // Teste do caminho feliz
    @Test
    void testFindAllCategories() {
        List<CategoryModel> categories = Collections.singletonList(categoryModel);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryModel> foundCategories = categoryService.findAll();

        assertNotNull(foundCategories);
        assertEquals(1, foundCategories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    // Teste do caminho triste
    @Test
    void testFindAllCategoriesEmpty() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<CategoryModel> foundCategories = categoryService.findAll();

        assertTrue(foundCategories.isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }

    // Teste do caminho feliz
    @Test
    void testFindCategoryById() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryModel));

        Optional<CategoryModel> foundCategory = categoryService.findById(id);

        assertTrue(foundCategory.isPresent());
        assertEquals("Beverages", foundCategory.get().getName());
        verify(categoryRepository, times(1)).findById(id);
    }

    // Teste do caminho triste
    @Test
    void testFindCategoryByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CategoryModel> foundCategory = categoryService.findById(id);

        assertFalse(foundCategory.isPresent());
        verify(categoryRepository, times(1)).findById(id);
    }

    // Teste do caminho feliz
    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepository).delete(categoryModel);

        categoryService.delete(categoryModel);

        verify(categoryRepository, times(1)).delete(categoryModel);
    }

    // Teste do caminho triste
    @Test
    void testDeleteCategoryFailure() {
        doThrow(new RuntimeException("Error deleting category")).when(categoryRepository).delete(categoryModel);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.delete(categoryModel);
        });

        assertEquals("Error deleting category", exception.getMessage());
        verify(categoryRepository, times(1)).delete(categoryModel);
    }
}
