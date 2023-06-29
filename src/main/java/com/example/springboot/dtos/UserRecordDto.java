package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String nome, @NotBlank String email, @NotBlank String login,
		@NotBlank String senha){
    
}
