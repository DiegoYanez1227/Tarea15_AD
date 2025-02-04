package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Alumno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer nia;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private char genero;
	private String ciclo;
	private String curso;
	private int grupo;
	
	
	public Alumno() {
		
	}


	public Alumno(String nombre, String apellidos, LocalDate fechaNacimiento,char genero, String ciclo, String curso, int grupo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.genero=genero;
		this.ciclo = ciclo;
		this.curso = curso;
	}
	
	public Alumno(int nia, String nombre, String apellidos, LocalDate fechaNacimiento,char genero, String ciclo, String curso, int grupo) {
		this(nombre,apellidos,fechaNacimiento,genero, ciclo, curso, grupo);
		this.nia=nia;
	}

	

	public Integer getNia() {
		return nia;
	}


	public void setNia(Integer nia) {
		this.nia = nia;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public char getGenero() {
		return genero;
	}


	public void setGenero(char genero) {
		this.genero = genero;
	}


	public String getCiclo() {
		return ciclo;
	}


	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getGrupo() {
		return grupo;
	}


	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(ciclo, other.ciclo)
				&& Objects.equals(curso, other.curso) && Objects.equals(fechaNacimiento, other.fechaNacimiento)
				&& genero == other.genero && Objects.equals(grupo, other.grupo) && Objects.equals(nia, other.nia)
				&& Objects.equals(nombre, other.nombre);
	}


	@Override
	public int hashCode() {
		return Objects.hash(apellidos, ciclo, curso, fechaNacimiento, genero, grupo, nia, nombre);
	}


	@Override
	public String toString() {
		return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + ", genero=" + genero + ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo
				+ "]";
	}

	
}
