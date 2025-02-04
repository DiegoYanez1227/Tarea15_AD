package ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Grupo;

public class FicheroJSON {

	private static final String RUTA_JSON = "grupos.json";

	
	public String generarFichero(List<Grupo> grupos) {
        guardarComoJSON(grupos);
        return RUTA_JSON;
    }

	public List<Grupo> leerFichero(String ruta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    private void guardarComoJSON(List<Grupo> grupos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            gson.toJson(grupos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   

}
