package model;

import java.util.List;

import model.Empleado;

public interface AlumnoDAO {

	
	int aniadirAlumno(Alumno alumno);					//Insert
			
	int aniadirGrupo(Grupo grupo);						//Insert
	
	List<Alumno> obtenerTodosLosAlumnos();				//Get
	
	int modificarNombrePorNia(int nia);					//Update
	
	void eliminarPorNia(int id);						//delete NIA
	
	void eliminarPorCurso(String curso);				//delete Curso
	
	
}


