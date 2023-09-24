package com.foro.alura.alura.usuario;

import com.foro.alura.alura.modelo.Usuario;

public record DatosRespuestaUsuario(Long id, String nombre, String email) {
    public DatosRespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }


}
