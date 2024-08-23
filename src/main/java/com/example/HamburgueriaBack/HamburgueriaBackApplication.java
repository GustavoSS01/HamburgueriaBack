package com.example.HamburgueriaBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
