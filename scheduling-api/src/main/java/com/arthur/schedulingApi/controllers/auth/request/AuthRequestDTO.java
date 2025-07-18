package com.arthur.schedulingApi.controllers.auth.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "O nome de usuário não pode estar em branco.")
        String userName,

        @NotBlank(message = "A senha não pode estar em branco.")
        String password
) {}
