package ficheros;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;

public class FicheroBinario {

	private static final String RUTA_BINARIO = "grupos.dat";

	public String generarFichero(List<Alumno> alumnos) {
	    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTA_BINARIO, true))) {
	        for (Alumno alumno : alumnos) {
	            outputStream.writeObject(alumno);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return RUTA_BINARIO;
	}
	
	public List<Alumno> leerFichero(String ruta) {
	    List<Alumno> alumnos = new ArrayList<Alumno>();
	    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ruta))) {
	        while (true) {
	            try {
	                Alumno alumno = (Alumno) inputStream.readObject();
	                alumnos.add(alumno);
	            } catch (EOFException e) {
	                break;  
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return alumnos;
	}
}
