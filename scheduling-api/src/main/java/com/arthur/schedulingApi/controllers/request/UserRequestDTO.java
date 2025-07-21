package com.arthur.schedulingApi.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco.")
        String name,

        @NotBlank(message = "O e-mail não pode estar em branco.")
        @Email(message = "O formato do e-mail é inválido.")
        String email,

        @NotBlank(message = "A senha não pode estar em branco.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        String password,

        @NotBlank(message = "O telefone não pode estar em branco.")
        @Pattern(regexp = "^[0-9]{10,11}$", message = "O telefone deve conter entre 10 e 11 dígitos numéricos.")
        String phoneNumber
) {}


