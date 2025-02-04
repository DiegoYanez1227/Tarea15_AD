package vista;


import java.util.List;

import model.Alumno;
import model.Grupo;



/**
 * Interfaz que define los métodos necesarios para interactuar con la vista en un sistema de gestión de alumnos y grupos.
 * Proporciona métodos para mostrar información y pedir entradas del usuario.
 */
public interface IVista {

	/**
	 * Muestra un menú al usuario y obtiene la opción seleccionada.
	 * @return La opción seleccionada por el usuario.
	 */
	int menu();

	/**
	 * Solicita los datos de un alumno al usuario.
	 * @return Un objeto {@link Alumno} con los datos introducidos por el usuario.
	 */
	Alumno pedirAlumno();

	/**
	 * Solicita los datos de un grupo al usuario.
	 * @return Un objeto {@link Grupo} con los datos introducidos por el usuario.
	 */
	Grupo pedirGrupo();

	/**
	 * Muestra los datos de un alumno.
	 * @param alumno El objeto {@link Alumno} cuyos datos se deben mostrar.
	 */
	void mostrarAlumno(Alumno alumno);

	/**
	 * Muestra la lista de alumnos.
	 * @param alumnos La lista de objetos {@link Alumno} que se deben mostrar.
	 */
	void mostrarAlumnos(List<Alumno> alumnos);

	/**
	 * Muestra la lista de cursos disponibles.
	 * @param cursos La lista de nombres de cursos a mostrar.
	 */
	void mostrarCursos(List<String> cursos);

	/**
	 * Muestra los datos de un grupo.
	 * @param grupo El objeto {@link Grupo} cuyos datos se deben mostrar.
	 */
	void mostrarGrupo(Grupo grupo);

	/**
	 * Muestra la lista de grupos.
	 * @param grupos La lista de objetos {@link Grupo} que se deben mostrar.
	 */
	void mostrarGrupos(List<Grupo> grupos);

	/**
	 * Muestra la ruta de un archivo.
	 * @param ruta La ruta del archivo a mostrar.
	 */
	void mostrarRutaDeFichero(String ruta);

	/**
	 * Muestra un mensaje al usuario.
	 * @param mensaje El mensaje que se debe mostrar al usuario.
	 */
	void mostrarMensaje(String mensaje);

	/**
	 * Solicita al usuario la ruta de un archivo.
	 * @return La ruta del archivo introducida por el usuario.
	 */
	String pedirRuta();

	/**
	 * Solicita al usuario el NIA de un alumno.
	 * @return El NIA del alumno introducido por el usuario.
	 */
	int pedirNia();

	/**
	 * Solicita al usuario el nombre de un alumno.
	 * @return El nombre del alumno introducido por el usuario.
	 */
	String pedirNombre();

	/**
	 * Solicita al usuario el nombre del curso.
	 * @return El nombre del curso introducido por el usuario.
	 */
	String pedirCurso();

	/**
	 * Solicita al usuario una confirmación.
	 * @return `true` si el usuario confirma, `false` si lo rechaza.
	 */
	boolean pedirConfirmacion();



}
