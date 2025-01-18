package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import model.Alumno;
import model.Grupo;

public class Menu {
	
	private KeyboardReader reader;
	private AlumnoDAO dao;
	
	public Menu() {
		reader= new KeyboardReader();
		dao= AlumnoDAOImpl.getInstance();
	}
	
	public void init() {
		int opcion;
		do {
			
		menu();
		opcion= reader.nextInt();
		
		switch(opcion) {
		case 1: 
			insertAlumno(); 
			break;
		case 2: 
			insertGrupo();
			break;
		case 3: 
			listAll();
			break;
		case 4: 
			update();
			break;
		case 5: 
			delete();
			break;
		case 6: 
			delete();
			break;
		case 7: 
			delete();
			break;
		case 8: 
			delete();
			break;
		case 9: 
			delete();
			break;
		case 10: 
			delete();
			break;
		case 0: 
			System.out.println("\n Saliendo del programa... \n");
			break;
		default: 
			System.err.println("El número introducido no se corresponde con una instruccion valida");
		}
		
		}while(opcion!=0);
	}
	
	
	public void menu() {
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
		System.out.println("8. Eliminar alumnos por apellido");
		System.out.println("9. Guardar todos los alumnos en un fichero JSON");
		System.out.println("10. Leer un fichero JSON de alumnos y guardarlos en la base de datos");
		System.out.print("\nOpción:");
	}
	
	public void insertAlumno() {
		System.out.println("\nINSERIÓN DE UN NUEVO ALUMNO");
		System.out.println("-------------------------------\n");
		
		System.out.print("Introduzca el nombre (sin apellidos) del alumno:"); 
		String nombre= reader.nextLine();
		System.out.print("Introduzca los apellidos del alumno:"); 
		String apellidos= reader.nextLine();
		System.out.print("Introduzca la fecha de nacimiento del alumno(formato dd/MM/yyyy):"); 
		LocalDate fechaNacimiento= reader.nextLocalDate();
		System.out.print("Introduzca el genero del alumno:"); 
		String genero= reader.nextLine();
		System.out.print("Introduzca el ciclo del alumno:"); 
		String ciclo= reader.nextLine();
		System.out.print("Introduzca el curso del alumno:"); 
		String curso= reader.nextLine();
		System.out.print("Introduzca el grupo del alumno:"); 
		String grupo= reader.nextLine();
		
		System.out.println();
		System.out.println();
		
		dao.aniadirAlumno(new Alumno(nombre, apellidos, fechaNacimiento, genero.charAt(0), ciclo, curso, grupo));
		
	}
	
	public void insertGrupo() {
		System.out.println("\nINSERIÓN DE UN NUEVO GRUPO");
		System.out.println("-------------------------------\n");
		
		System.out.print("Introduzca el nombre del grupo:"); 
		String nombre= reader.nextLine();
			
		dao.aniadirGrupo(new Grupo(nombre));
		
	}
	
	public void listAll() {
		System.out.println("\n LISTADO DE TODOS LOS ALUMNOS");
		System.out.println("----------------------------------");
		
		List<Alumno> result=dao.obtenerTodosLosAlumnos();
		if(result.isEmpty()) {
			System.out.println("No hay alumnos registrados en la base de datos");
		}else {
			printCabeceraTabelaAlumno();
			result.forEach(this::printAlumno);
			
		}
		System.out.println("\n");
	}
	
	private  void guardarFicheroBinario() {
		//Lógica para guardar alumnos en un fichero Binario
		System.out.println("Funcionalidad para guardar alumnos en un fichero binario.");
		dao.guardarAlumnoBinario(alumnos);

	}	
	
	public void listByID() {
		System.out.println("\nBÚSQUEDA DE ALUMNO POR ID");
		System.out.println("------------------------------\n");
		
		System.out.print("Introduzca el ID del empleado a buscar: ");
		int id= reader.nextInt();
		Alumno empleado= dao.getById(id);
		
		if(empleado == null) {
			System.out.println("No hay empleados registrados en la base de datos con ese ID");
		}else {
			printCabeceraTabelaAlumno();
			printAlumno(empleado);
		}
		System.out.println("\n");
	}
	
	public void update() {
		System.out.println("\nACTUALIZACIÓN DE UN ALUMNO");
		System.out.println("-------------------------------\n");
		
		System.out.println("Introduzca el ID del alumno a buscar");
		int id= reader.nextInt();
		
		Alumno alumno=dao.getById(id);		//NECESITAMOS HACER ESTO DENTRO DE OTRO METODO YA QUE ES LO QUE ULTIZAMOS DENTRO DE EL OBTENER X ID
		if(alumno==null) {
			System.out.println("No hay alumnos registrados en la base de datos con ese id");
		}else {
			printCabeceraTabelaAlumno();
			printAlumno(alumno);
			System.out.println("\n");
			
			System.out.printf("Introduzca el nombre(sin apellidos) del alumno (%s)",alumno.getNombre());
			String nombre= reader.nextLine();
			nombre=(nombre.isBlank()) ? alumno.getNombre() :nombre;
			
			System.out.printf("Introduzca los apellidos del alumno (%s)",alumno.getApellidos());
			String apellidos= reader.nextLine();
			apellidos=(apellidos.isBlank()) ? alumno.getApellidos() :apellidos;
			
			System.out.printf("Introduzca la fecha de nacimiento del alumno (formato dd/MM/yyyy) (%s)",
					alumno.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			String strfechaNacimiento= reader.nextLine();
			LocalDate fechaNacimiento=(strfechaNacimiento.isBlank()) ? alumno.getFechaNacimiento() :
				LocalDate.parse(strfechaNacimiento,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			
			System.out.printf("Introduzca el género del alumno (%s): ", alumno.getGenero());
			String generoInput = reader.nextLine();
			char genero = generoInput.isBlank() ? alumno.getGenero() : generoInput.charAt(0);
			alumno.setGenero(genero);
			
			System.out.printf("Introduzca el ciclo del alumno (%s)",alumno.getCiclo());
			String ciclo= reader.nextLine();
			ciclo=(ciclo.isBlank()) ? alumno.getCiclo() :ciclo;
			
			System.out.printf("Introduzca el curso del alumno (%s)",alumno.getCurso());
			String curso= reader.nextLine();
			curso=(curso.isBlank()) ? alumno.getCurso() :curso;
			
			System.out.printf("Introduzca el grupo del alumno (%s)",alumno.getGrupo());
			String grupo= reader.nextLine();
			grupo=(grupo.isBlank()) ? alumno.getGrupo() :grupo;
			
			alumno.setNombre(nombre);
			alumno.setApellidos(apellidos);
			alumno.setFechaNacimiento(fechaNacimiento);
			alumno.setGenero(genero);
			alumno.setCiclo(ciclo);
			alumno.setCurso(curso);
			alumno.setGrupo(grupo);
			
			dao.update(alumno);
			
			System.out.println("");
			System.out.printf("Empleado con ID %s actualizado", alumno.getNia());
			
			
		}
	}
	
	public void delete() {
		
		System.out.println("\n BORRADO DE UN ALUMNO");
		System.out.println("--------------------------\n");
		
		System.out.print("Introduzca el ID del alumno a borrar:");
		int id= reader.nextInt();
		
		System.out.printf("¿Está usted seguro de querer eliminar al alumno con ID=%s? (s/n):", id);
		String borrar= reader.nextLine();
		
		if(borrar.equalsIgnoreCase("s")) {
			dao.delete(id);
			System.out.printf("El alumno con ID %s se ha borrado\n", id);
		}
		
		System.out.println("\n");
	}
	
	
	
	private void printCabeceraTabelaAlumno() {
		System.out.printf("%2s %30s %8s %10s %25s", "ID", "NOMBRE","FEC.NAC.", "PUESTO", "EMAIL" );
		System.out.println("");
		IntStream.range(1,100).forEach(x -> System.out.print("-"));
		System.out.println("\n");
	}
	
	private void printAlumno(Alumno alumno) {
		System.out.printf("%2s %30s %9s %10s %25s\n",
				alumno.getNia(),
				alumno.getNombre()+" "+alumno.getApellidos(),
				alumno.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				alumno.getGenero(),
				alumno.getCiclo(),
				alumno.getCurso(),
				alumno.getGrupo()
				);
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
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		
		
		LocalDate nextLocalDate() {
			return LocalDate.parse(next(),DateTimeFormatter.ofPattern(FORMATO_FECHA));
		}
		
		String nextLine() {
			String str="";
			try {
				if(st.hasMoreElements()) {
					str=st.nextToken("\n");
				}else {
					str= br.readLine();
				}
			}catch(IOException e) {
				System.err.println("Error leyendo del teclado");
				e.printStackTrace();
			}
			
			return str;
		}
	}
}
