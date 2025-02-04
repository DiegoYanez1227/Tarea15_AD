package controlador;

import java.util.List;

import ficheros.*;
import model.*;
import vista.*;

public class Controlador {
	
	// pasar todo a metodos dentro  del switch 
	
	IFichero ficheroTXT= new FicheroTXT();
	IFichero ficheroJSON= new FicheroJSON();
	
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
				Alumno a= vista.pedirAlumno(); 	
				int numeroDeAlumnosInsertado=modelo.aniadirAlumno(a);
				if(numeroDeAlumnosInsertado==1) {
					vista.mostrarMensaje("El alumno ha sido insertado correctamente.");
				}else {
					vista.mostrarMensaje("El alumno no ha podido ser insertado debido a algun error.");
				}
				break;
			case 2: 
				//INSERTAR GRUPO
				Grupo grupo= vista.pedirGrupo();	
				break;
			case 3: 
				//VISUALIZAR TODOS LOS ALUMNOS
				List<Alumno> alumnos=modelo.obtenerTodosLosAlumnos();	
				if(alumnos!=null) {
					vista.mostrarAlumnos(alumnos);
				}else {
					vista.mostrarMensaje("Ha habido un error con la obtención de los datos desde la base de datos. ");
				}	
				break;
			case 4: 
				//GUARDAR DENTRO DE UN FICHERO RECOGIENDO DE LA BASE DE DATOS LA INFORMACION
				List<Alumno> alumnosParaFichero=modelo.obtenerTodosLosAlumnos();	
				
				if(alumnosParaFichero!=null) {
					String ruta=ficheroTXT.generarFichero(alumnosParaFichero);
					vista.mostrarRutaDeFichero(ruta);
				}else {
					vista.mostrarMensaje("No se ha podido exportar los datos dentro de un fichero de texto.");
				}
				break;
			case 5: 
				//LEER ALUMNOS DESDE UN FICHERO DE TEXTO Y GUARDARLOS EN LA BASE DE DATOS
				String ruta=vista.pedirRuta();
				List<Alumno> alumnosDesdeFichero= ficheroTXT.leerFichero(ruta);
				if(alumnosDesdeFichero!=null) {
					vista.mostrarAlumnos(alumnosDesdeFichero);
					modelo.aniadirAlumnos(alumnosDesdeFichero);
					vista.mostrarMensaje("Los anteriores se han guardado correctamente dentro de la base de datos desde el fichero de texto.");
				}else {
					vista.mostrarMensaje("No se ha podido mostrar los alumnos desde el fichero de texto debido a un error.");
				}
				break;
			case 6: 
				// MODIFICAR EL NOMBRE DE UN ALUMNO SEGUN SU NIA
				int nia= vista.pedirNia();
				Alumno alumnoUpdate =modelo.obtenerAlumnoPorNIA(nia);
				vista.mostrarMensaje("Aqui esta el alumno con nia "+ nia+ " del quieres cambiar el nombre.");
				vista.mostrarAlumno(alumnoUpdate);
				String nombre= vista.pedirNombre();
				modelo.modificarNombrePorNia(nia, nombre);
				break;
			case 7: 
				//ELIMINARO ALUMNO SEGUN EL NIA
				int niaEliminar= vista.pedirNia();
				if(vista.pedirConfirmacion()) {
					modelo.eliminarPorNia(niaEliminar);
					vista.mostrarMensaje("Se ha efectuado el eliminado del alumno con nia "+niaEliminar);
				}else {
					vista.mostrarMensaje("No se ha efectuado el eliminado del alumno con nia "+niaEliminar+" ya que no lo ha confirmado");
				}
				break;
			case 8: 
				//ELIMINAR TODOS LOS ALUMNOS DE UN CURSO
				List<String> cursos= modelo.mostrarCursos();
				vista.mostrarCursos(cursos);
				String curso= vista.pedirCurso();
				if(vista.pedirConfirmacion()) {
					modelo.eliminarPorCurso(curso);
					vista.mostrarMensaje("Se ha eliminado con exito todos los alumnos que pertenecen a ");
				}else {
					vista.mostrarMensaje("No se ha efectuado el eliminado del alumno con nia "+niaEliminar+" ya que no lo ha confirmado");
				}
				
				

				break;
			case 9: 

				break;
			case 10: 

				break;
			case 0: 
				System.out.println("\n Saliendo del programa... \n");
				break;
			default: 
				System.err.println("El número introducido no se corresponde con una instruccion valida");
			}

		}while(opcion!=0);

	}


}
