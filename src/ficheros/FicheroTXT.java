package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;

public class FicheroTXT {

	private static final String RUTA_TXT = "grupos.txt";
	
	/**
     * Método para guardar una lista de grupos en un archivo TXT.
     * @param Lista de grupos leída del TXT
     * @return Ruta del archivo TXT
     */
	public String generarFichero(List<Alumno> alumnos) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_TXT, true))) {
	        for (Alumno alumno : alumnos) {
	            writer.write(alumno.getNia() + "," + alumno.getNombre() + "," + alumno.getApellidos() + "," + 
	                         alumno.getFechaNacimiento().toString() + "," + alumno.getGenero() + "," + 
	                         alumno.getCiclo() + "," + alumno.getCurso() + "," + alumno.getGrupo());
	            writer.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return RUTA_TXT;
	}

	/**
     * Método para leer un archivo TXT y convertirlo en una lista de grupos.
     * @param Ruta del archivo TXT
     * @return Lista de grupos leída del TXT
     */
	public List<Alumno> leerFichero(String ruta) {
	    List<Alumno> alumnos = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] datos = line.split(",");
	            Alumno alumno = new Alumno(
	                Integer.parseInt(datos[0]),
	                datos[1],
	                datos[2],
	                LocalDate.parse(datos[3]),
	                datos[4].charAt(0),
	                datos[5],
	                datos[6],
	                Integer.parseInt(datos[7])
	            );
	            alumnos.add(alumno);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return alumnos;
	}

}
