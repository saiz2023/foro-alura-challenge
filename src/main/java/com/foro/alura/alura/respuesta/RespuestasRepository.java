package com.foro.alura.alura.respuesta;

import com.foro.alura.alura.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestasRepository extends JpaRepository<Respuesta,Long> {
}
