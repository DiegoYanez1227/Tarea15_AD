package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import model.Alumno;
import model.Grupo;

public class VistaConsola implements IVista{

	private Scanner sc;
	private KeyboardReader reader;

	public VistaConsola() {
		reader= new KeyboardReader();
		sc = new Scanner(System.in);
	}


	/*

	 * Guardar todos los grupos (con toda su información como
		atributos) en un fichero XML o JSON. Para cada grupo se
		guardará también el listado de alumnos de ese grupo. Los datos
		del alumno serán atributos en el XML.
	 * Leer un fichero XML o JSON de grupos (con en formato anterior)
		y guardarlos en la BD
	 */

	public int menu() {
		System.out.println("SISTEMA DE GESTION DE ALUMNOS");
		System.out.println("===============================");
		System.out.println("-> Introduzca una opción de entre las siguientes");
		System.out.println("0. Salir");
		System.out.println("1. Insertar un nuevo alumno");
		System.out.println("2. Insertar un nuevo grupo");
		System.out.println("3. Mostrar todos los alumnos (en consola)");
		System.out.println("4. Guardar todos los alumnos en un fichero");
		System.out.println("5. Leer alumnos de un fichero y guardarlos en la base de datos");
		System.out.println("6. Modificar el nombre de un alumno (usando su PK)");
		System.out.println("7. Eliminar un alumno (usando su PK)");
		System.out.println("8. Eliminar alumnos por curso");
		System.out.println("9. Guardar todos los grupos en un fichero JSON");
		System.out.println("10. Leer un fichero JSON de alumnos y guardarlos en la base de datos");
		System.out.print("\nOpción:");

		System.out.println("Introduce una opcion");
		int opcion= sc.nextInt();

		return opcion;

	}

	@Override
	public Alumno pedirAlumno() {

		System.out.println("\nINSERIÓN DE UN NUEVO ALUMNO");
		System.out.println("-------------------------------\n");

		System.out.print("Introduzca el nombre (sin apellidos) del alumno:"); 
		String nombre= sc.nextLine();
		sc.nextLine();
		System.out.print("Introduzca los apellidos del alumno:"); 
		String apellidos= sc.nextLine();
		sc.nextLine();
		System.out.print("Introduzca la fecha de nacimiento del alumno(formato dd/MM/yyyy):"); 
		LocalDate fechaNacimiento= reader.nextLocalDate();
		System.out.print("Introduzca el genero del alumno:"); 
		String genero= sc.nextLine();
		sc.nextLine();
		System.out.print("Introduzca el ciclo del alumno:"); 
		String ciclo= sc.nextLine();
		sc.nextLine();
		System.out.print("Introduzca el curso del alumno:"); 
		String curso= sc.nextLine();
		sc.nextLine();
		System.out.print("Introduzca el grupo del alumno:"); 
		int grupo= sc.nextInt();

		System.out.println();
		System.out.println();
		Alumno a=new Alumno( nombre,  apellidos,  fechaNacimiento, genero.charAt(0),  ciclo,  curso, grupo);

		return a;
	}

	@Override
	public Grupo pedirGrupo() {

		System.out.println("\nINSERIÓN DE UN NUEVO GRUPO");
		System.out.println("-------------------------------\n");

		System.out.print("Introduzca el nombre del grupo:"); 
		String nombre= sc.nextLine();

		Grupo grupo= new Grupo(nombre);
		return grupo;
	}

	private void printCabeceraTablaAlumno() {
		System.out.printf("%2s %30s %8s %15s %10s %10s %2s", "CODIGO", "NOMBRE", "FEC.NAC.", "GENERO", "CICLO", "CURSO", "GRUPO" );
		System.out.println("");
		IntStream.range(1,100).forEach(x -> System.out.print("-"));
		System.out.println("\n");
	}

	@Override
	public void mostrarAlumno(Alumno alumno) {
		System.out.printf("%2s %30s %8s %15s %10s %10s %2s\n",
				alumno.getNia(),
				alumno.getNombre()+" "+alumno.getApellidos(),
				alumno.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				alumno.getGenero(),
				alumno.getCiclo(),
				alumno.getCurso(),
				alumno.getGrupo()
				);

	}

	@Override
	public void mostrarAlumnos(List<Alumno> alumnos) {
		System.out.println("\n LISTADO DE TODOS LOS ALUMNOS");
		System.out.println("----------------------------------");

		if(alumnos.isEmpty()) {
			System.out.println("No hay alumnos registrados en la base de datos");
		}else {
			printCabeceraTablaAlumno();
			alumnos.forEach(this::mostrarAlumno);

		}
		System.out.println("\n");	
	}

	private void printCabeceraTablaGrupo() {
		System.out.printf("%2s %10s ", "CODIGO GRUPO", "NOMBRE");
		System.out.println("");
		IntStream.range(1,100).forEach(x -> System.out.print("-"));
		System.out.println("\n");
	}


	@Override
	public void mostrarCursos(List<String> cursos) {
		for (String curso : cursos) {
			System.out.println("- " + curso);
		}
	}

	@Override
	public void mostrarGrupo(Grupo grupo) {
		System.out.printf("%2s %30s\n",
				grupo.getId_grupo(),
				grupo.getNombre()
				);


	}

	@Override
	public void mostrarGrupos(List<Grupo> grupos) {
		System.out.println("\n LISTADO DE TODOS LOS ALUMNOS");
		System.out.println("----------------------------------");

		if(grupos.isEmpty()) {
			System.out.println("No hay grupos registrados en la base de datos");
		}else {
			printCabeceraTablaGrupo();
			grupos.forEach(this::mostrarGrupo);

		}
		System.out.println("\n");	

	}


	@Override
	public void mostrarRutaDeFichero(String ruta) {
		System.out.println("La ruta del fichero es: "+ruta);
	}


	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);

	}


	@Override
	public String pedirRuta() {
		System.out.println(" Introduzca la ruta del fichero del cual quieras guardar los datos");
		String ruta = sc.nextLine();
		return ruta;
	}

	@Override
	public int pedirNia() {
		System.out.println(" Introduzca el nia del alumno ");
		int nia= sc.nextInt();
		return nia;
	}


	@Override
	public String pedirNombre() {
		System.out.println(" Introduzca el nuevo nombre del alumno");
		String nombre = sc.nextLine();
		return nombre;
	}


	@Override
	public String pedirCurso() {
		System.out.println(" Introduzca el curso del que quieras eliminar todos los alumnos");
		String curso = sc.nextLine();
		return curso;
	}

	@Override
	public boolean pedirConfirmacion() {
		System.out.println("¿Estas seguro de querer eliminar el alumno?");
		String respuesta=sc.nextLine();
		if(respuesta.equalsIgnoreCase("si")) {
			return true;
		}
		return false;
	}




	static class KeyboardReader{

		private static final String FORMATO_FECHA = "dd/MM/yyyy";
		BufferedReader br;
		StringTokenizer st;

		public KeyboardReader(){
			br= new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st= new StringTokenizer(br.readLine());
				} catch (IOException e) {

				} 
			}

			return st.nextToken();
		}

		LocalDate nextLocalDate() {
			return LocalDate.parse(next(),DateTimeFormatter.ofPattern(FORMATO_FECHA));
		}

	}




}
