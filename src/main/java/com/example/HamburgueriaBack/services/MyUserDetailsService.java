package com.example.HamburgueriaBack.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // Aqui você deve injetar o repositório que carrega os dados do usuário
    // Exemplo:
    // @Autowired
    // private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui você busca o usuário pelo username no banco de dados
        // Substitua esse exemplo pelo código real
        if ("admin".equals(username)) {
            return new User("admin", "{noop}password", new ArrayList<>()); // {noop} indica que a senha não está codificada
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    }
}
