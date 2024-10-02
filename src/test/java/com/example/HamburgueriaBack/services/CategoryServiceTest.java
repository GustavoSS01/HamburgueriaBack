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

    @Test
    void testSaveCategory() {
        when(categoryRepository.save(categoryModel)).thenReturn(categoryModel);

        CategoryModel savedCategory = categoryService.save(categoryModel);

        assertNotNull(savedCategory);
        assertEquals("Beverages", savedCategory.getName());
        verify(categoryRepository, times(1)).save(categoryModel);
    }

    @Test
    void testExistsByName() {
        when(categoryRepository.existsByName("Beverages")).thenReturn(true);

        boolean exists = categoryService.existsByName("Beverages");

        assertTrue(exists);
        verify(categoryRepository, times(1)).existsByName("Beverages");
    }

    @Test
    void testFindAllCategories() {
        List<CategoryModel> categories = Collections.singletonList(categoryModel);
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryModel> foundCategories = categoryService.findAll();

        assertNotNull(foundCategories);
        assertEquals(1, foundCategories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testFindCategoryById() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryModel));

        Optional<CategoryModel> foundCategory = categoryService.findById(id);

        assertTrue(foundCategory.isPresent());
        assertEquals("Beverages", foundCategory.get().getName());
        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepository).delete(categoryModel);

        categoryService.delete(categoryModel);

        verify(categoryRepository, times(1)).delete(categoryModel);
    }
}
