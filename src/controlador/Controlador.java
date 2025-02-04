package controlador;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ficheros.*;
import model.*;
import vista.*;
/**
 * Clase Controlador que maneja la interacción entre la vista y el modelo.
 * Esta clase procesa las opciones del menú y ejecuta las acciones correspondientes, como añadir alumnos, 
 * modificar datos de alumnos, guardar y cargar información desde archivos de texto y JSON, y eliminar datos.
 */
public class Controlador {
	
	// Instancias de objetos para manejo de archivos de texto y JSON y Objeto para la obtenencion de informacion dentro de un fichero LOGGER
	static Logger Logger = LogManager.getLogger(Controlador.class);
	FicheroTXT ficheroTXT = new FicheroTXT();
	FicheroJSON ficheroJSON = new FicheroJSON();

	/**
	 * Método principal que inicia la ejecución del programa.
	 * Crea un modelo de datos (AlumnoDAO) y una vista (IVista), y luego ejecuta el controlador.
	 */
	public static void main(String[] args) {
		AlumnoDAO modelo1 = new AlumnoBD();
		IVista vista = new VistaConsola();
		new Controlador().ejecutar(modelo1, vista);
	}

	/**
	 * Ejecuta el controlador, mostrando el menú y procesando las opciones seleccionadas por el usuario.
	 * Realiza las operaciones correspondientes para añadir, modificar, eliminar y visualizar alumnos y grupos,
	 * además de guardar y cargar información desde archivos.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que muestra la información al usuario y recoge las entradas.
	 */
	public void ejecutar(AlumnoDAO modelo, IVista vista) {
		int opcion = 1;
		do {
			opcion = vista.menu();
			switch (opcion) {
			case 1:
				// Insertar un alumno
				aniadirAlumno(modelo, vista);
				break;
			case 2:
				// Insertar un grupo
				aniadirGrupo(modelo, vista);
				break;
			case 3:
				// Visualizar todos los alumnos
				listarAlumnos(modelo, vista);
				break;
			case 4:
				// Guardar en un fichero TXT
				guardarEnFicheroTxT(modelo, vista);
				break;
			case 5:
				// Leer alumnos desde un fichero de texto y guardarlos en la base de datos
				leerDesdeFicheroTXT(modelo, vista);
				break;
			case 6:
				// Modificar el nombre de un alumno según su NIA
				modificarNombrePorNia(modelo, vista);
				break;
			case 7:
				// Eliminar un alumno según su NIA
				eliminarPorNia(modelo, vista);
				break;
			case 8:
				// Eliminar todos los alumnos de un curso
				eliminarPorCurso(modelo, vista);
				break;
			case 9:
				// Leer los grupos desde la base de datos y guardarlos en un fichero JSON
				guardarEnFicheroJSON(modelo, vista);
				break;
			case 10:
				// Leer los grupos desde un fichero JSON y guardarlos en la base de datos
				leerDesdeFicheroJSON(modelo, vista);
				break;
			case 0:
				// Salir del programa
				vista.mostrarMensaje("Saliendo del programa...");
				break;
			default:
				// Introducción de un número no válido
				System.err.println("El número introducido no se corresponde con una instrucción válida");
			}
		} while (opcion != 0);
	}

	


	/**
	 * Añade un alumno a la base de datos.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void aniadirAlumno(AlumnoDAO modelo, IVista vista) {
		Alumno alumno = vista.pedirAlumno();
		int numeroDeAlumnosInsertados = modelo.aniadirAlumno(alumno);
		if (numeroDeAlumnosInsertados == 1) {
			vista.mostrarMensaje("El alumno ha sido insertado correctamente.");
			Logger.info("El alumno ha sido insertado correctamente.");
		} else {
			vista.mostrarMensaje("El alumno no ha podido ser insertado debido a algún error.");
		}
	}
	
	/**
	 * Añade un grupo a la base de datos.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void aniadirGrupo(AlumnoDAO modelo, IVista vista) {
		Grupo grupo = vista.pedirGrupo();
		int numeroDeGruposInsertados = modelo.aniadirGrupo(grupo);
		if (numeroDeGruposInsertados == 1) {
			vista.mostrarMensaje("El grupo ha sido insertado correctamente.");
			Logger.info("El grupo ha sido insertado correctamente.");
		} else {
			vista.mostrarMensaje("El grupo no ha podido ser insertado debido a algún error.");
		}
	}
	
	
	/**
	 * Muestra la lista de todos los alumnos en la vista.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void listarAlumnos(AlumnoDAO modelo, IVista vista) {
		List<Alumno> alumnos = modelo.obtenerTodosLosAlumnos();
		if (alumnos != null) {
			vista.mostrarAlumnos(alumnos);
			Logger.info("Se han mostrado correctamente los Alumnos");
		} else {
			vista.mostrarMensaje("Ha habido un error con la obtención de los datos desde la base de datos.");
		}
	}
	
	
	/**
	 * Guarda los alumnos obtenidos desde la base de datos en un fichero de texto.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void guardarEnFicheroTxT(AlumnoDAO modelo, IVista vista) {
		List<Alumno> alumnosParaFichero = modelo.obtenerTodosLosAlumnos();
		if (alumnosParaFichero != null) {
			String ruta = ficheroTXT.generarFichero(alumnosParaFichero);
			vista.mostrarRutaDeFichero(ruta);
			Logger.info("Los alumnos se han guardado correctamente dentro el fichero de texto desde la base de datos.");
		} else {
			vista.mostrarMensaje("No se ha podido exportar los datos dentro de un fichero de texto.");
		}
	}
	
	
	/**
	 * Lee los alumnos desde un fichero de texto y los guarda en la base de datos.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void leerDesdeFicheroTXT(AlumnoDAO modelo, IVista vista) {
		String ruta = vista.pedirRuta();
		List<Alumno> alumnosDesdeFichero = ficheroTXT.leerFichero(ruta);
		if (alumnosDesdeFichero != null) {
			vista.mostrarAlumnos(alumnosDesdeFichero);
			modelo.aniadirAlumnos(alumnosDesdeFichero);
			vista.mostrarMensaje("Los alumnos se han guardado correctamente dentro de la base de datos desde el fichero de texto.");
			Logger.info("Los alumnos se han guardado correctamente dentro de la base de datos desde el fichero de texto.");
		} else {
			vista.mostrarMensaje("No se ha podido mostrar los alumnos desde el fichero de texto debido a un error.");
		}
	}
	
	
	/**
	 * Modifica el nombre de un alumno de acuerdo a su NIA.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void modificarNombrePorNia(AlumnoDAO modelo, IVista vista) {
		int nia = vista.pedirNia();
		Alumno alumnoUpdate = modelo.obtenerAlumnoPorNIA(nia);
		vista.mostrarMensaje("Aquí está el alumno con NIA " + nia + " del que quieres cambiar el nombre.");
		vista.mostrarAlumno(alumnoUpdate);
		String nombre = vista.pedirNombre();
		modelo.modificarNombrePorNia(nia, nombre);
		Logger.info("Se ha modificado el nombre del alumno con nia "+nia+" a "+nombre);
	}
	
	/**
	 * Elimina un alumno de acuerdo a su NIA.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void eliminarPorNia(AlumnoDAO modelo, IVista vista) {
		int niaEliminar = vista.pedirNia();
		if (vista.pedirConfirmacion()) {
			modelo.eliminarPorNia(niaEliminar);
			vista.mostrarMensaje("Se ha eliminado con éxito el alumno con NIA " + niaEliminar);
			Logger.info("Se ha eliminado con éxito el alumno con NIA " + niaEliminar);
		} else {
			vista.mostrarMensaje("No se ha eliminado con éxito el alumno con NIA " + niaEliminar + " ya que no lo ha confirmado.");
		}
	}
	
	
	/**
	 * Elimina todos los alumnos pertenecientes a un curso.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void eliminarPorCurso(AlumnoDAO modelo, IVista vista) {
		List<String> cursos = modelo.obtenerCursos();
		vista.mostrarCursos(cursos);
		String curso = vista.pedirCurso();
		if (vista.pedirConfirmacion()) {
			modelo.eliminarPorCurso(curso);
			vista.mostrarMensaje("Se ha eliminado con éxito todos los alumnos que pertenecen al curso " + curso);
			Logger.info("Se ha eliminado con éxito todos los alumnos que pertenecen al curso " + curso);
		} else {
			vista.mostrarMensaje("No se ha eliminado con éxito todos los alumnos que pertenecen al curso " + curso);
		}
	}
	
	
	/**
	 * Guarda los grupos obtenidos desde la base de datos en un fichero JSON.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void guardarEnFicheroJSON(AlumnoDAO modelo, IVista vista) {
		List<Grupo> gruposParaFichero = modelo.obtenerTodosLosGrupos();
		if (gruposParaFichero != null) {
			String rutaGrupos = ficheroJSON.generarFichero(gruposParaFichero);
			vista.mostrarRutaDeFichero(rutaGrupos);
			Logger.info("Se ha podido leer los alumnos de la base de datos e introducirlo dentro del fichero JSON.");
		} else {
			vista.mostrarMensaje("No se ha podido exportar los datos dentro de un fichero JSON.");
		}
	}
	
	
	/**
	 * Lee los grupos desde un fichero JSON y los añade a la base de datos.
	 * @param modelo El modelo de datos que interactúa con la base de datos.
	 * @param vista La vista que interactúa con el usuario.
	 */
	private void leerDesdeFicheroJSON(AlumnoDAO modelo, IVista vista) {
		String rutaGrupos = vista.pedirRuta();
		List<Grupo> gruposDesdeFichero = ficheroJSON.leerFichero(rutaGrupos);
		if (gruposDesdeFichero != null) {
			vista.mostrarGrupos(gruposDesdeFichero);
			modelo.aniadirGrupos(gruposDesdeFichero);
			vista.mostrarMensaje("Los anteriores se han guardado correctamente dentro de la base de datos desde el fichero de JSON.");
			Logger.info("Se ha podido leer del fichero JSON e introducirlo dentro de la base de datos.");
		} else {
			vista.mostrarMensaje("No se ha podido mostrar los grupos desde el fichero de JSON debido a un error.");
			
		}
	}
}
