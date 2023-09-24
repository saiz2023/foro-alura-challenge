package com.foro.alura.alura.topico;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String descripcion,
        Long autor,
        Long curso

) {
}
