package com.example.HamburgueriaBack.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
//import java.time.LocalDateTime;
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
    @Column(nullable = false)
    private byte image;
    @Column(nullable = false, unique = false, length = 20)
    private String category;

}
