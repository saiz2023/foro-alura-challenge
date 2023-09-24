package com.foro.alura.alura.controller;


import com.foro.alura.alura.infra.security.SecurityConfigurations;
import com.foro.alura.alura.modelo.Usuario;
import com.foro.alura.alura.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("usuarios")
public class usuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> Registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        String passwordCodificado = passwordEncoder.encode(datos.pass());

        Usuario usuario= usuarioRepository.save(new Usuario(datos,passwordCodificado));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(@PageableDefault(size = 10) Pageable pagina) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pagina);
        Page<DatosRespuestaUsuario> datosRespuestaUsuarios = usuarios.map(DatosRespuestaUsuario::new);
        return ResponseEntity.ok(datosRespuestaUsuarios);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> modificarMedico(@RequestBody @Valid DatosActualizarUsuario datos){
        String passwordCodificado = passwordEncoder.encode(datos.pass());

        Usuario usuario = usuarioRepository.getReferenceById(datos.id());
        usuario.actualizarUsuario(datos,passwordCodificado);
        return ResponseEntity.ok(new DatosRespuestaUsuario (usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }
@GetMapping("/{id}")
    public ResponseEntity<DatosUsuario> obtenerUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosUsuario(usuario));
    }

}
