package com.example.HamburgueriaBack.services;

import com.example.HamburgueriaBack.models.ClientModel;
import com.example.HamburgueriaBack.repositories.ClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private ClientModel clientModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientModel = new ClientModel();
        clientModel.setName("John Doe");
        clientModel.setPhone(123456789);
        clientModel.setEmail("johndoe@example.com");
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(clientModel)).thenReturn(clientModel);

        ClientModel savedClient = clientService.save(clientModel);

        assertNotNull(savedClient);
        assertEquals("John Doe", savedClient.getName());
        verify(clientRepository, times(1)).save(clientModel);
    }

    @Test
    void testExistsByName() {
        when(clientRepository.existsByName("John Doe")).thenReturn(true);

        boolean exists = clientService.existsByName("John Doe");

        assertTrue(exists);
        verify(clientRepository, times(1)).existsByName("John Doe");
    }

    @Test
    void testExistsByPhone() {
        when(clientRepository.existsByPhone(123456789)).thenReturn(true);

        boolean exists = clientService.existsByPhone(123456789);

        assertTrue(exists);
        verify(clientRepository, times(1)).existsByPhone(123456789);
    }
    @Test
    void testExistsByEmail() {
        when(clientRepository.existsByEmail("johndoe@example.com")).thenReturn(true);

        boolean exists = clientService.existsByEmail("johndoe@example.com");

        assertTrue(exists);
        verify(clientRepository, times(1)).existsByEmail("johndoe@example.com");
    }

    @Test
    void testFindAllClients() {
        List<ClientModel> clients = Arrays.asList(clientModel);
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientModel> foundClients = clientService.findAll();

        assertNotNull(foundClients);
        assertEquals(1, foundClients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testFindClientById() {
        UUID id = UUID.randomUUID();
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientModel));

        Optional<ClientModel> foundClient = clientService.findById(id);

        assertTrue(foundClient.isPresent());
        assertEquals("John Doe", foundClient.get().getName());
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).delete(clientModel);

        clientService.delete(clientModel);

        verify(clientRepository, times(1)).delete(clientModel);
    }
}