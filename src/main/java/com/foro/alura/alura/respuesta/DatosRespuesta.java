package com.foro.alura.alura.respuesta;

import com.foro.alura.alura.modelo.Respuesta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String Respuesta,
        LocalDateTime fecha,
        String usuario,
        String topico
) {
    public DatosRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo()
        );
    }
}
