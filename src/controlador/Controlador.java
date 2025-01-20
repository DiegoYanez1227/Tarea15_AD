package controlador;

import model.*;
import vista.*;

public class Controlador {

	public static void main(String[] args) {
		
		AlumnoDAO modelo1 = new AlumnoBD();
		AlumnoDAO modelo2 = new AlumnoFichero();
		AlumnoDAO modelo3 = new AlumnoXML();
		AlumnoDAO modelo4 = new AlumnoJSON();
		
		VistaConsola vista= new VistaConsola();
		
		new Controlador().ejecutar(modelo1, vista);
		
	}
	
	public void ejecutar(AlumnoDAO modelo, VistaConsola vista) {
		
	}

}
