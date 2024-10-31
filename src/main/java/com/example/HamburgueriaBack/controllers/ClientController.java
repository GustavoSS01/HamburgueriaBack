package com.example.HamburgueriaBack.controllers;

import ch.qos.logback.core.net.server.Client;
import com.example.HamburgueriaBack.dtos.ClientDto;
import com.example.HamburgueriaBack.dtos.ProductDto;
import com.example.HamburgueriaBack.models.ClientModel;
import com.example.HamburgueriaBack.models.ProductModel;
import com.example.HamburgueriaBack.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RequestMapping("/client")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) { this.clientService = clientService; }

    @Operation(summary = "Cadastrar novo cliente", description = "Endpoint para cadastrar um novo cliente")
    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto){
        if(clientService.existsByPhone(clientDto.getPhone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Número de telefone já cadastrado.");
        }
        if(clientService.existsByEmail(clientDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        //productModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @Operation(summary = "Listar todos os clientes", description = "Endpoint para listar todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @Operation(summary = "Buscar um cliente pelo ID", description = "Endpoint para buscar um cliente pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        return clientModelOptional.<ResponseEntity<Object>>map(clientModel -> ResponseEntity.status(HttpStatus.OK).body(clientModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado"));
    }

    @Operation(summary = "Deletar um cliente", description = "Endpoint para deletar um cliente pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }

    @Operation(summary = "Atualizar um cliente", description = "Endpoint para atualizar um cliente pelo seu ID")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientDto clientDto){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        clientModel.setId(clientModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }
}
