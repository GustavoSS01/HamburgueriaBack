package com.example.HamburgueriaBack.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public class ProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String image;

    @NotBlank
    private String category;

    private Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public Float getPrice() {
        if(price == null) {
            return 0.00f;
        } else {
            return price;
        }
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}