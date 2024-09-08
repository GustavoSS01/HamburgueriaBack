package com.example.HamburgueriaBack.repositories;

import com.example.HamburgueriaBack.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    boolean existsByName(String name);
    boolean existsByPhone(int phone);
    boolean existsByEmail(String email);
}
