package vista;

import java.util.ArrayList;

import model.Alumno;
import model.Grupo;

public interface IVista {
	
	 void mostrarAlumnos(ArrayList<Alumno> alumnos);

	    void mostrarCursos(ArrayList<String> cursos);

	    String pedirCurso();

	    Alumno pedirDatosAlumno();

	    Grupo pedirDatosGrupo();
	    
	    String pedirString();
	    
	    int pedirInt();

}
