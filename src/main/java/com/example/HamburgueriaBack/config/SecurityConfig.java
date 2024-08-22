package com.example.HamburgueriaBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()  // Permitir acesso público a URLs que começam com /public/
                        .anyRequest().authenticated()               // Exige autenticação para qualquer outra URL
                )
                .formLogin(form -> form
                        .loginPage("/login")                        // Configura a URL da página de login personalizada
                        .permitAll()                                // Permitir que todos acessem a página de login
                )
                .logout(LogoutConfigurer::permitAll)                // Permitir que todos acessem a URL de logout

                .csrf().disable();                              // Desativa CSRF para simplificar; em produção, considere habilitar

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
