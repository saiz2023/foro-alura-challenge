package com.foro.alura.alura.modelo;

import com.foro.alura.alura.curso.DatosRegistroCurso;
import com.foro.alura.alura.usuario.DatosActualizarUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String categoria;


	public Curso(DatosRegistroCurso datos) {
		this.nombre = datos.nombre();
        this.categoria = datos.categoria();
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void actualizarCurso(DatosRegistroCurso datos) {
		if(datos.nombre()!=null)this.nombre = datos.nombre();
		if(datos.categoria()!=null)this.categoria = datos.categoria();
	}
}
