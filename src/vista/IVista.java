package vista;


import java.util.List;

import model.Alumno;
import model.Grupo;

public interface IVista {
	
		int menu();
		
		Alumno pedirAlumno();
		
		Grupo pedirGrupo();
		
		void mostrarAlumno(Alumno alumno);
			
		void mostrarAlumnos(List<Alumno> alumnos);
		
		void mostrarRutaDeFichero(String ruta);
		
		void mostrarCursos(List<String> cursos);
	   
		void mostrarMensaje(String mensaje);
		
		String pedirRuta();
		
		int pedirNia();
		
		String pedirNombre();
		
		boolean pedirConfirmacion();
		
		String pedirCurso();

		
		
}
