package com.foro.alura.alura.topico;

import com.foro.alura.alura.curso.CursoRepository;
import com.foro.alura.alura.modelo.Curso;
import com.foro.alura.alura.modelo.StatusTopico;
import com.foro.alura.alura.modelo.Topico;
import com.foro.alura.alura.modelo.Usuario;
import com.foro.alura.alura.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ServiceTopico {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public Topico guardarTopico(DatosRegistroTopico datos){
        Usuario autor = usuarioRepository.findById(datos.usuario())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.curso())
                .orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        var fecha = LocalDateTime.now();
        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                fecha,
               autor,
                curso
        );
        return topico;
    }

    public Topico actualizarTopico(Topico topico, DatosActualizarTopico datos) {
        Usuario autor = null;
        Curso curso = null;
        System.out.println("anterior: " + datos.autor());
        if(datos.autor()!=null) {
            System.out.println("despues: " + datos.autor());
             autor = usuarioRepository.findById(datos.autor())
                    .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        }else autor=topico.getAutor();
        if(datos.curso()!=null) {
             curso = cursoRepository.findById(datos.curso())
                   .orElseThrow(() -> new NoSuchElementException("Curso no encontrado"));
        }else curso=topico.getCurso();

        return topico.actualizarTopico(topico,datos,autor,curso);


    }
}
