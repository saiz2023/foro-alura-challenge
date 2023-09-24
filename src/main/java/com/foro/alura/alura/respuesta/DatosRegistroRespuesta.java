package com.foro.alura.alura.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotNull
        Long autor,
        @NotNull
        Long topico,
        @NotBlank
        String mensaje
) {
}
