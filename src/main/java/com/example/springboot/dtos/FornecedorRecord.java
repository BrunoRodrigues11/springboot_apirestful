package com.example.springboot.dtos;

import org.springframework.format.annotation.NumberFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorRecord (@NotBlank String razaoSocial, @NotBlank String nomeFantasia, @NotBlank String cnpj, @NotBlank String endereco, @NotBlank String fone, @NotBlank String email, @NumberFormat @NotNull int inscricaoEstadual){
    
}
