package ficheros;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Grupo;

public class FicheroJSON {

	private static final String RUTA_JSON = "grupos.json";

	/**
     * Método para guardar una lista de grupos en un archivo JSON.
     * @param Lista de grupos leída del JSON
     * @return Ruta del archivo JSON
     */
	public String generarFichero(List<Grupo> grupos) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            gson.toJson(grupos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return RUTA_JSON;
    }
	 /**
     * Método para leer un archivo JSON y convertirlo en una lista de grupos.
     * @param ruta Ruta del archivo JSON
     * @return Lista de grupos leída del JSON
     */
	public List<Grupo> leerFichero(String ruta) {
        try (FileReader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Grupo>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

   
   
    

    

}
