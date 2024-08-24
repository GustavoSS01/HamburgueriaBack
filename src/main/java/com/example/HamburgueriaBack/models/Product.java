package com.example.HamburgueriaBack.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

}
