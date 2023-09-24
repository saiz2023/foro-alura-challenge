package com.foro.alura.alura.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,
        String mensaje,
        Long autor,
        Long topico


) {

}
