package com.example.HamburgueriaBack.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    @NotBlank
    private String name;

    @NotBlank
    private long num_prod;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNum_prod() {
        return num_prod;
    }
}
