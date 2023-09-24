package com.foro.alura.alura.curso;

import com.foro.alura.alura.modelo.Curso;

public record DatosRespuestaCurso(Long id, String nombre, String categoria) {
    public DatosRespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
