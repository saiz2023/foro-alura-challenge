package com.foro.alura.alura.respuesta;

import com.foro.alura.alura.modelo.Curso;
import com.foro.alura.alura.modelo.Respuesta;
import com.foro.alura.alura.modelo.Topico;
import com.foro.alura.alura.modelo.Usuario;
import com.foro.alura.alura.topico.TopicoRepository;
import com.foro.alura.alura.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ServiceRespuesta {
    @Autowired
    private RespuestasRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public Respuesta guardar(DatosRegistroRespuesta datos) {
        Usuario autor = usuarioRepository.findById(datos.autor())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        Topico topico = topicoRepository.findById(datos.topico())
                .orElseThrow(() -> new NoSuchElementException("topico no encontrado"));
        Respuesta respuesta= new Respuesta(
                datos.mensaje(),
                autor,
                topico
        );
        return respuesta;
    }

    public Respuesta actualizarRespuesta(Respuesta respuesta, DatosActualizarRespuesta datos) {
        Usuario autor = null;
       Topico topico = null;

        if(datos.autor()!=null) {
            autor=usuarioRepository.findById(datos.autor())
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        }else autor=respuesta.getAutor();

        if(datos.topico()!=null){
            topico = topicoRepository.findById(datos.topico())
                    .orElseThrow(() -> new NoSuchElementException("topico no encontrado"));
        }else topico=respuesta.getTopico();

        return respuesta.actualizarREspuesta(respuesta,datos,autor,topico);

    }
}
