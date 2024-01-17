package com.picpaychallenge.dtos.requests;

import com.picpaychallenge.enums.UserTypeEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UserRequest(
        @NotBlank(message = "Nome é obrigatório")
        String firstName,
        @NotBlank(message = "Sobrenome é obrigatório")
        String lastName,
        @NotBlank(message = "Documento é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "Documento inválido")
        String document,
        @NotNull
        BigDecimal balance,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha é obrigatório")
        @Size(min=8, message = "A senha deve ter no mínimo 8 caracteres")
        String password,
        @NotNull
        UserTypeEnum userType
) { }
