package model;

import java.util.Objects;

public class Grupo {
	
	private Integer id_grupo;
	private String nombre;
	
	public Grupo() {}

	public Grupo(String nombre) {
		this.nombre = nombre;
	}
	
	public Grupo(int id_grupo, String nombre) {
		this.id_grupo = id_grupo;
		this.nombre = nombre;
	}
	
	

	public Integer getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_grupo, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return id_grupo == other.id_grupo && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Grupo [id_grupo=" + id_grupo + ", nombre=" + nombre + "]";
	}
}
