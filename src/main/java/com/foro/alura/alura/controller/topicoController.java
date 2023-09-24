package com.foro.alura.alura.controller;

import com.foro.alura.alura.modelo.Topico;
import com.foro.alura.alura.topico.*;
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
@RequestMapping("/topicos")
public class topicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private ServiceTopico serviceTopico;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> Registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(serviceTopico.guardarTopico(datos));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listar(@PageableDefault(size = 10) Pageable pagina) {
        Page<Topico> topico = topicoRepository.findAll(pagina);
        Page<DatosRespuestaTopico> datosRespuestaTopico = topico.map(DatosRespuestaTopico::new);
        return ResponseEntity.ok(datosRespuestaTopico);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> modificar(@RequestBody @Valid DatosActualizarTopico datos){
       Topico topico= topicoRepository.getReferenceById(datos.id());
        topico= serviceTopico.actualizarTopico(topico,datos);
        return ResponseEntity.ok(new DatosRespuestaTopico (topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        Topico topico=topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id){
        Topico topico=topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }


}
