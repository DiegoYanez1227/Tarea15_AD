package controlador;

import java.util.List;

import model.*;
import vista.*;

public class Controlador {

	public static void main(String[] args) {
		
		AlumnoDAO modelo1 = new AlumnoBD();
		AlumnoDAO modelo2 = new AlumnoFichero();
		AlumnoDAO modelo3 = new AlumnoXML();
		AlumnoDAO modelo4 = new AlumnoJSON();
		
		VistaConsola vista= new VistaConsola();
		
		new Controlador().ejecutar(modelo1, vista);
		
	}
	
	public void ejecutar(AlumnoDAO modelo, VistaConsola vista) {
		
	}
	
	int aniadirAlumno(Alumno alumno){
		
	}				
	
	int aniadirGrupo(Grupo grupo) {
		//Insert
	}
	
	List<Alumno> obtenerTodosLosAlumnos(){
		//Get
	}
	
	int modificarNombrePorNia(int nia, String nombre) {
		//Update
	}
	
	void eliminarPorNia(int nia) {
		//delete NIA
	}
	
	void eliminarPorCurso(String curso) {
		
	}

}
