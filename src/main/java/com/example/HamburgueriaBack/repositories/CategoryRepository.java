package com.example.HamburgueriaBack.repositories;

import com.example.HamburgueriaBack.models.CategoryModel;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
    CategoryModel findByName(String name);
    boolean existsByName(String name);

}
