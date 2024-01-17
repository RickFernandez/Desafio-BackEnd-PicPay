package com.picpaychallenge.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(
        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "O campo mensagem é obrigatório")
        String message
) { }
