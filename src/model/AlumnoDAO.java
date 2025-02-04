package model;

import java.util.List;



public interface AlumnoDAO {

	
	int aniadirAlumno(Alumno alumno);						//Insert
			
	int aniadirAlumnos(List<Alumno> alumnos);				//Insert
	
	int aniadirGrupo(Grupo grupo);							//Insert
	
	/**
	 * Devuelve la lista de todos los alumnos, 
	 * si existe algun problema devolveremos nulo y en caso de 
	 * no obtener nada devolvera una lista vacia.
	 * @return
	 */
	List<Alumno> obtenerTodosLosAlumnos();					//Get
	
	Alumno obtenerAlumnoPorNIA(int nia);					//Get
	
	int modificarNombrePorNia(int nia, String nombre);		//Update
	
	void eliminarPorNia(int nia);							//delete NIA
	
	void eliminarPorCurso(String curso);					//delete Curso
	
	List<String> mostrarCursos();
	
	
}


