package ficheros;

import java.util.List;

import model.Grupo;

public class FicheroXML {

	public String generarFichero(List<Grupo> grupos) {
        guardarComoXML(grupos, "grupos.json");
        return "Archivos generados correctamente: grupos.json y grupos.xml";
    }
	
	private void guardarComoXML(List<Grupo> grupos, String ruta) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("Grupos");
            doc.appendChild(rootElement);

            for (Grupo grupo : grupos) {
                Element grupoElement = doc.createElement("Grupo");
                rootElement.appendChild(grupoElement);

                // Atributos del grupo
                grupoElement.setAttribute("id", String.valueOf(grupo.getId()));
                grupoElement.setAttribute("nombre", grupo.getNombre());

                // Lista de alumnos dentro del grupo
                for (Alumno alumno : grupo.getAlumnos()) {
                    Element alumnoElement = doc.createElement("Alumno");
                    grupoElement.appendChild(alumnoElement);

                    // Atributos del alumno
                    alumnoElement.setAttribute("nia", String.valueOf(alumno.getNia()));
                    alumnoElement.setAttribute("nombre", alumno.getNombre());
                    alumnoElement.setAttribute("apellidos", alumno.getApellidos());
                    alumnoElement.setAttribute("fechaNacimiento", alumno.getFechaNacimiento().toString());
                    alumnoElement.setAttribute("genero", alumno.getGenero());
                    alumnoElement.setAttribute("ciclo", alumno.getCiclo());
                    alumnoElement.setAttribute("curso", alumno.getCurso());
                    alumnoElement.setAttribute("grupo", alumno.getGrupo());
                }
            }

            // Guardar XML en archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(ruta));

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
