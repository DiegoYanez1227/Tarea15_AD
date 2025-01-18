package dao;

import java.util.List;

import model.Alumno;
import model.Grupo;

public interface AlumnoDAO {

	
	int aniadirAlumno(Alumno alumno);
	
	int aniadirGrupo(Grupo grupo);
	
	List<Alumno> obtenerTodosLosAlumnos();
	
	String guardarAlumnoBinario(List<Alumno> alumnos);
	
	void leerAlumnoJSON();
	
	int modificarNombrePorNia(int nia);
	
	void eliminarPorNia(int id);
	
	void eliminarPorCurso(String curso);
	
	void guardarGrupoJSON(List<Grupo> grupos);
	
	void leerGrupoJSON();
	
	
}


