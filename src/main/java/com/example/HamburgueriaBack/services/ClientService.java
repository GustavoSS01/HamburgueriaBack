package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.ClientModel;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) { return clientRepository.save(clientModel);}

    public boolean existsByName(String name) { return clientRepository.existsByName(name); }

    public boolean existsByPhone(int phone) { return clientRepository.existsByPhone(phone); }

    public boolean existsByEmail(String email) { return clientRepository.existsByEmail(email); }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
