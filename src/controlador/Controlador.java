package controlador;

import model.*;

public class Controlador {

	public static void main(String[] args) {
		
		AlumnoDAO modelo1 = new AlumnoBD();
		AlumnoDAO modelo2 = new AlumnoFichero();
		AlumnoDAO modelo3 = new AlumnoXML();
		AlumnoDAO modelo4 = new AlumnoJSON();
		
	}

}
