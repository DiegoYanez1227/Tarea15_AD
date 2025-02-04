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

    /**
     * Método genérico para leer un fichero JSON o XML según la extensión de la ruta.
     * @param ruta Ruta del fichero (JSON o XML)
     * @return Lista de grupos leída del archivo
     */
    public List<Grupo> leerFichero(String ruta) {
        if (ruta.endsWith(".json")) {
            return leerFicheroJSON(ruta);
        } else if (ruta.endsWith(".xml")) {
            return leerFicheroXML(ruta);
        } else {
            System.err.println("Formato de archivo no soportado: " + ruta);
            return null;
        }
    }

    /**
     * Método para leer un archivo JSON y convertirlo en una lista de grupos.
     * @param ruta Ruta del archivo JSON
     * @return Lista de grupos leída del JSON
     */
    private List<Grupo> leerFicheroJSON(String ruta) {
        try (FileReader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Grupo>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    

}
