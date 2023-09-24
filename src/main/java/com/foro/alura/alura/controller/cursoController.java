package com.foro.alura.alura.controller;

import com.foro.alura.alura.curso.CursoRepository;
import com.foro.alura.alura.curso.DatosRegistroCurso;
import com.foro.alura.alura.curso.DatosRespuestaCurso;
import com.foro.alura.alura.modelo.Curso;
import com.foro.alura.alura.modelo.Usuario;
import com.foro.alura.alura.usuario.DatosActualizarUsuario;
import com.foro.alura.alura.usuario.DatosRespuestaUsuario;
import com.foro.alura.alura.usuario.DatosUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class cursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> Registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = cursoRepository.save(new Curso(datos));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaCurso>> listar(@PageableDefault(size = 10) Pageable pagina){
        Page<Curso> cursos = cursoRepository.findAll(pagina);
        Page<DatosRespuestaCurso> datosRespuestaCursos = cursos.map(DatosRespuestaCurso::new);
        return ResponseEntity.ok(datosRespuestaCursos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> obtenerCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaCurso> modificar(@RequestBody @Valid DatosRegistroCurso datos){
       Curso curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizarCurso(datos);
        return ResponseEntity.ok(new DatosRespuestaCurso (curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        cursoRepository.deleteById(id);

    }

}
