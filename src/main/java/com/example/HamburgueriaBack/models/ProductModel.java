package com.example.HamburgueriaBack.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
@Data
public class ProductModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @Column(nullable = false, length = 30)
    private String description;
    @Column(nullable = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    @JsonBackReference
    private CategoryModel category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public void setCategory(CategoryModel byName) {
        this.category = byName;
    }
}