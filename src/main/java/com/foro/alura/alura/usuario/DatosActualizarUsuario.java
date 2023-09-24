package com.foro.alura.alura.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosActualizarUsuario(
        Long id,
        String nombre,
        @Email
        String email,
        String pass
) {
}
