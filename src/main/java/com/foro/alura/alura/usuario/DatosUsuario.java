package com.foro.alura.alura.usuario;

import com.foro.alura.alura.modelo.Usuario;

public record DatosUsuario(Long id, String nombre, String email, String pass) {
    public DatosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPass());
    }


}
