package com.example.HamburgueriaBack.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @Column(nullable = false, unique = true, length = 30)
    private String description;
    @Column(nullable = false, unique = true)
    private byte image;
    @Column(nullable = false, unique = false, length = 20)
    private String category;

}
