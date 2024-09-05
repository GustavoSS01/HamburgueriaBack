package com.example.HamburgueriaBack.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "CATEGORY")
@Data
public class CategoryModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCategory;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @Column(nullable = false)
    private long num_prod;

    public UUID getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(UUID idCategory) {
        this.idCategory = idCategory;
    }
}
