package com.example.HamburgueriaBack;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@OpenAPIDefinition(info = @Info(title = "API da Hamburguria", version = "1.0", description = "Documentação da API Hamburguria"))
@SpringBootApplication
@RestController
public class HamburgueriaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamburgueriaBackApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Olá mundo";
	}
}