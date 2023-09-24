package com.foro.alura.alura.modelo;

import com.foro.alura.alura.respuesta.DatosActualizarRespuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	private Boolean solucion = false;
	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;

	public  Respuesta(String mensaje, Usuario autor, Topico topico) {
		this.mensaje = mensaje;
        this.autor = autor;
        this.topico = topico;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public Respuesta actualizarREspuesta(Respuesta respuesta, DatosActualizarRespuesta datos, Usuario autor, Topico topico) {
		if (datos.mensaje()!=null)this.mensaje = datos.mensaje();
        this.autor = autor;
        this.topico = topico;
        return this;
	}
}
