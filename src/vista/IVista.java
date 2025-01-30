package vista;

import java.util.ArrayList;
import java.util.List;

import model.Alumno;
import model.Grupo;

public interface IVista {
	
		int menu();
		
		Alumno pedirAlumno();
		
		void mostrarOperacionCorrecta();
		
		void mostrarRutaDeFichero(String ruta);
		
		void mostrarAlumno(Alumno alumno);
		
	 	void mostrarAlumnos(List<Alumno> alumnos);

	    void mostrarCursos(ArrayList<String> cursos);

	    String pedirCurso();

	    Alumno pedirDatosAlumno();

	    Grupo pedirDatosGrupo();

		void mostrarCursos(List<String> cursos);
	   

}
