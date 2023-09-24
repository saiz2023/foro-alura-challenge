package com.foro.alura.alura.modelo;

import com.foro.alura.alura.topico.DatosActualizarTopico;
import com.foro.alura.alura.topico.DatosRegistroTopico;
import com.foro.alura.alura.topico.TopicoRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	@OneToMany(mappedBy = "topico")
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, LocalDateTime fecha, Usuario autor, Curso curso) {
		this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fecha;
        this.autor = autor;
        this.curso = curso;
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
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Topico actualizarTopico(Topico topico, DatosActualizarTopico datos, Usuario autor, Curso curso) {
		if (datos.titulo()!=null) this.titulo=datos.titulo();
		if (datos.descripcion()!=null) this.mensaje=datos.descripcion();
		this.autor=autor;
		this.curso=curso;
        return this;
	}
}
