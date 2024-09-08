package com.example.HamburgueriaBack.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClientDto {

    @NotBlank
    private String name;

    @NotNull
    private int phone;

    @NotBlank
    private String email;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank int phone) {
        this.phone = phone;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }
}
