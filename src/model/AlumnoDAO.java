package model;

import java.util.List;


/**
 * Interface que define las operaciones CRUD para gestionar los alumnos y grupos en una base de datos.
 */
public interface AlumnoDAO {

    /**
     * Inserta un alumno en la base de datos.
     * 
     * @param alumno El alumno a insertar.
     * @return El número de filas afectadas.
     */
    int aniadirAlumno(Alumno alumno); // Insert

    /**
     * Inserta una lista de alumnos en la base de datos.
     * 
     * @param alumnos La lista de alumnos a insertar.
     * @return El número de filas afectadas.
     */
    int aniadirAlumnos(List<Alumno> alumnos); // Insert

    /**
     * Inserta un grupo en la base de datos.
     * 
     * @param grupo El grupo a insertar.
     * @return El número de filas afectadas.
     */
    int aniadirGrupo(Grupo grupo); // Insert

    /**
     * Inserta una lista de grupos en la base de datos.
     * 
     * @param alumnos La lista de grupos a insertar.
     * @return El número de filas afectadas.
     */
    int aniadirGrupos(List<Grupo> alumnos); // Insert

    /**
     * Obtiene la lista de todos los alumnos desde la base de datos.
     * Si ocurre algún problema, devuelve null, y si no hay alumnos, devuelve una lista vacía.
     * 
     * @return La lista de alumnos o null en caso de error.
     */
    List<Alumno> obtenerTodosLosAlumnos(); // Get

    /**
     * Obtiene la lista de todos los grupos desde la base de datos.
     * 
     * @return La lista de grupos.
     */
    List<Grupo> obtenerTodosLosGrupos();

    /**
     * Obtiene un alumno a partir de su NIA.
     * 
     * @param nia El NIA del alumno.
     * @return El alumno correspondiente al NIA.
     */
    Alumno obtenerAlumnoPorNIA(int nia); // Get

    /**
     * Modifica el nombre de un alumno según su NIA.
     * 
     * @param nia El NIA del alumno.
     * @param nombre El nuevo nombre del alumno.
     * @return El número de filas afectadas.
     */
    int modificarNombrePorNia(int nia, String nombre); // Update

    /**
     * Elimina un alumno de la base de datos mediante su NIA.
     * 
     * @param nia El NIA del alumno a eliminar.
     */
    void eliminarPorNia(int nia); // Delete NIA

    /**
     * Elimina todos los alumnos pertenecientes a un curso.
     * 
     * @param curso El curso cuyos alumnos se eliminarán.
     */
    void eliminarPorCurso(String curso); // Delete Curso

    /**
     * Muestra los cursos disponibles en la base de datos.
     * 
     * @return Una lista de cursos.
     */
    List<String> obtenerCursos();
}
