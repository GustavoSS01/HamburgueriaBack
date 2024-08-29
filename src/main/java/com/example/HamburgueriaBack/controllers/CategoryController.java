package com.example.HamburgueriaBack.controllers;

import com.example.HamburgueriaBack.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
}
