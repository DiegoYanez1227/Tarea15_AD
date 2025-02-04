package controlador;

import java.util.List;

import ficheros.*;
import model.*;
import vista.*;

public class Controlador {
	
	// pasar todo a metodos dentro  del switch 
	
	FicheroTXT ficheroTXT= new FicheroTXT();
	FicheroJSON ficheroJSON= new FicheroJSON();
	
	public static void main(String[] args) {
		
		
		AlumnoDAO modelo1 = new AlumnoBD();


		IVista vista= new VistaConsola();

		new Controlador().ejecutar(modelo1, vista);

	}

	public void ejecutar(AlumnoDAO modelo, IVista vista) {
		int opcion=1;
		do {
			opcion =vista.menu();

			switch(opcion) {
			case 1: 
				//INSERTAR ALUMNO
				aniadirAlumno(modelo, vista);
				break;
			case 2: 
				//INSERTAR GRUPO
				aniadirGrupo(modelo, vista);
				break;
			case 3: 
				//VISUALIZAR TODOS LOS ALUMNOS
				listarAlumnos(modelo, vista);	
				break;
			case 4: 
				//GUARDAR DENTRO DE UN FICHERO RECOGIENDO DE LA BASE DE DATOS LA INFORMACION
				guardarEnFicheroTxT(modelo, vista);
				break;
			case 5: 
				//LEER ALUMNOS DESDE UN FICHERO DE TEXTO Y GUARDARLOS EN LA BASE DE DATOS
				leerDesdeFicheroTXT(modelo, vista);
				break;
			case 6: 
				// MODIFICAR EL NOMBRE DE UN ALUMNO SEGUN SU NIA
				modificarNombrePorNia(modelo, vista);
				break;
			case 7: 
				//ELIMINARO ALUMNO SEGUN EL NIA
				eliminarPorNia(modelo, vista);
				break;
			case 8: 
				//ELIMINAR TODOS LOS ALUMNOS DE UN CURSO
				eliminarPorCurso(modelo, vista);
				break;
			case 9: 
				//LEE LOS GRUPOS DESDE LA BASE DE DATOS Y LOS INTRODUCE EN UN FICHERO JSON
				guardarEnFicheroJSON(modelo, vista);
				break;
			case 10: 
				//LEE LOS GRUPOS DESDE UN FICHERO JSON Y LOS INTRODUCE EN LA BASE DE DATOS
				leerDesdeFicheroJSON(modelo, vista);
				break;
			case 0: 
				System.out.println("\n Saliendo del programa... \n");
				break;
			default: 
				System.err.println("El número introducido no se corresponde con una instruccion valida");
			}

		}while(opcion!=0);

	}

	private void leerDesdeFicheroJSON(AlumnoDAO modelo, IVista vista) {
		String rutaGrupos=vista.pedirRuta();
		List<Grupo> gruposDesdeFichero= ficheroJSON.leerFichero(rutaGrupos);
		if(gruposDesdeFichero!=null) {
			vista.mostrarGrupos(gruposDesdeFichero);
			modelo.aniadirGrupos(gruposDesdeFichero);
			vista.mostrarMensaje("Los anteriores se han guardado correctamente dentro de la base de datos desde el fichero de texto.");
		}else {
			vista.mostrarMensaje("No se ha podido mostrar los alumnos desde el fichero de texto debido a un error.");
		}
	}

	private void guardarEnFicheroJSON(AlumnoDAO modelo, IVista vista) {
		List<Grupo> gruposParaFichero=modelo.obtenerTodosLosGrupos();	
		
		if(gruposParaFichero!=null) {
			String rutaGrupos=ficheroJSON.generarFichero(gruposParaFichero);
			vista.mostrarRutaDeFichero(rutaGrupos);
		}else {
			vista.mostrarMensaje("No se ha podido exportar los datos dentro de un fichero de texto.");
		}
	}

	private void eliminarPorCurso(AlumnoDAO modelo, IVista vista) {
		List<String> cursos= modelo.mostrarCursos();
		vista.mostrarCursos(cursos);
		String curso= vista.pedirCurso();
		if(vista.pedirConfirmacion()) {
			modelo.eliminarPorCurso(curso);
			vista.mostrarMensaje("Se ha eliminado con exito todos los alumnos que pertenecen al curso "+curso);
		}else {
			vista.mostrarMensaje("No se ha eliminado con exito todos los alumnos que pertenecen al curso "+curso);
		}
	}

	private void eliminarPorNia(AlumnoDAO modelo, IVista vista) {
		int niaEliminar= vista.pedirNia();
		if(vista.pedirConfirmacion()) {
			modelo.eliminarPorNia(niaEliminar);
			vista.mostrarMensaje("Se ha eliminado con exito el alumno con nia "+niaEliminar);
		}else {
			vista.mostrarMensaje("No se ha eliminado con exito el alumno con nia "+niaEliminar+" ya que no lo ha confirmado");
		}
	}

	private void modificarNombrePorNia(AlumnoDAO modelo, IVista vista) {
		int nia= vista.pedirNia();
		Alumno alumnoUpdate =modelo.obtenerAlumnoPorNIA(nia);
		vista.mostrarMensaje("Aqui esta el alumno con nia "+ nia+ " del quieres cambiar el nombre.");
		vista.mostrarAlumno(alumnoUpdate);
		String nombre= vista.pedirNombre();
		modelo.modificarNombrePorNia(nia, nombre);
	}

	private void leerDesdeFicheroTXT(AlumnoDAO modelo, IVista vista) {
		String ruta=vista.pedirRuta();
		List<Alumno> alumnosDesdeFichero= ficheroTXT.leerFichero(ruta);
		if(alumnosDesdeFichero!=null) {
			vista.mostrarAlumnos(alumnosDesdeFichero);
			modelo.aniadirAlumnos(alumnosDesdeFichero);
			vista.mostrarMensaje("Los anteriores se han guardado correctamente dentro de la base de datos desde el fichero de texto.");
		}else {
			vista.mostrarMensaje("No se ha podido mostrar los alumnos desde el fichero de texto debido a un error.");
		}
	}

	private void guardarEnFicheroTxT(AlumnoDAO modelo, IVista vista) {
		List<Alumno> alumnosParaFichero=modelo.obtenerTodosLosAlumnos();	
		
		if(alumnosParaFichero!=null) {
			String ruta=ficheroTXT.generarFichero(alumnosParaFichero);
			vista.mostrarRutaDeFichero(ruta);
		}else {
			vista.mostrarMensaje("No se ha podido exportar los datos dentro de un fichero de texto.");
		}
	}

	private void listarAlumnos(AlumnoDAO modelo, IVista vista) {
		List<Alumno> alumnos=modelo.obtenerTodosLosAlumnos();	
		if(alumnos!=null) {
			vista.mostrarAlumnos(alumnos);
		}else {
			vista.mostrarMensaje("Ha habido un error con la obtención de los datos desde la base de datos. ");
		}
	}

	private void aniadirGrupo(AlumnoDAO modelo, IVista vista) {
		Grupo grupo= vista.pedirGrupo();	
		int numeroDeGurposInsertado=modelo.aniadirGrupo(grupo);
		if(numeroDeGurposInsertado==1) {
			vista.mostrarMensaje("El grupo ha sido insertado correctamente.");
		}else {
			vista.mostrarMensaje("El grupo no ha podido ser insertado debido a algun error.");
		}
	}

	private void aniadirAlumno(AlumnoDAO modelo, IVista vista) {
		Alumno a= vista.pedirAlumno(); 	
		int numeroDeAlumnosInsertado=modelo.aniadirAlumno(a);
		if(numeroDeAlumnosInsertado==1) {
			vista.mostrarMensaje("El alumno ha sido insertado correctamente.");
		}else {
			vista.mostrarMensaje("El alumno no ha podido ser insertado debido a algun error.");
		}
	}


}
