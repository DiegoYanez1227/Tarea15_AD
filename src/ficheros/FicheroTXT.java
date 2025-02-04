package ficheros;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import model.Alumno;
import model.AppendingObjectOutputStream;

public class FicheroTXT {

	
	public String generarFichero(List<Alumno> alumnos) {
		int result = 0;

		try (FileOutputStream fileOut = new FileOutputStream(FICHEROBINARIO, true);
				ObjectOutputStream objOut = new AppendingObjectOutputStream(fileOut)) {

			objOut.writeObject(alumno);
			result = 1; // Indicar éxito en la escritura
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}


	public List<Alumno> leerFichero(String ruta) {
		// TODO Auto-generated method stub
		return null;
	}

}
