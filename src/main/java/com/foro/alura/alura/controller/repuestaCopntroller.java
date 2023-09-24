package com.foro.alura.alura.controller;

import com.foro.alura.alura.modelo.Respuesta;
import com.foro.alura.alura.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
public class repuestaCopntroller {
    @Autowired
    private RespuestasRepository respuestasRepository;

    @Autowired
    private ServiceRespuesta serviceRespuesta;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> Registrar(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder){
        Respuesta respuesta = respuestasRepository.save(serviceRespuesta.guardar(datos));
        DatosRespuesta datosRespuesta = new DatosRespuesta(respuesta);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuesta>> listar(@PageableDefault(size = 10) Pageable pagina) {
        Page<Respuesta> respuesta = respuestasRepository.findAll(pagina);
        Page<DatosRespuesta> datosRespuesta = respuesta.map(DatosRespuesta::new);
        return ResponseEntity.ok(datosRespuesta);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuesta> modificar(@RequestBody @Valid DatosActualizarRespuesta datos){
        Respuesta respuesta= respuestasRepository.getReferenceById(datos.id());
        respuesta= serviceRespuesta.actualizarRespuesta(respuesta,datos);
        return ResponseEntity.ok(new DatosRespuesta (respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        Respuesta respuesta=respuestasRepository.getReferenceById(id);
        respuestasRepository.delete(respuesta);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuesta> obtenerTopico(@PathVariable Long id){
        Respuesta respuesta=respuestasRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuesta(respuesta));
    }



}
