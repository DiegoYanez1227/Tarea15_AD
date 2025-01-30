package controlador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import ficheros.FicheroJSON;
import ficheros.FicheroTXT;
import ficheros.IFichero;

import model.*;
import vista.*;

public class Controlador {
	
	IFichero ficheroTXT= new FicheroTXT();
	IFichero ficheroJSON= new FicheroJSON();
	
	public static void main(String[] args) {
		
		
		AlumnoDAO modelo1 = new AlumnoBD();


		IVista vista= new VistaConsola();

		new Controlador().ejecutar(modelo1, vista);

	}

	public void ejecutar(AlumnoDAO modelo, IVista vista) {
		do {
			int opcion =vista.menu();

			switch(opcion) {
			case 1: 
				Alumno a= vista.pedirAlumno(); 	
				int numeroDeAlumnosInsertado=modelo.aniadirAlumno(a);
				if(numeroDeAlumnosInsertado==1) {
					vista.mostrarOperacionCorrecta();
				}else {
					vista.mostrarOperacionCorrecta();// cambiar a mostrar mensaje y pasarle un String 
				}
				break;
			case 2: 
				//vista.insertGrupo();		//
				break;
			case 3: 
				List<Alumno> alumnos=modelo.obtenerTodosLosAlumnos();	
				if(alumnos!=null) {
					vista.mostrarAlumnos(alumnos);
				}else {
					vista.mostrarOperacionCorrecta();
				}	
				break;
			case 4: 
				List<Alumno> alumnosParaFichero=modelo.obtenerTodosLosAlumnos();	
				
				if(alumnosParaFichero!=null) {
					String ruta=ficheroTXT.generarFichero(alumnosParaFichero);
					vista.mostrarRutaDeFichero(ruta);
				}else {
					vista.mostrarOperacionCorrecta();
				}
					
				
				break;
			case 5: 

				break;
			case 6: 

				break;
			case 7: 

				break;
			case 8: 

				break;
			case 9: 

				break;
			case 10: 

				break;
			case 0: 
				System.out.println("\n Saliendo del programa... \n");
				break;
			default: 
				System.err.println("El número introducido no se corresponde con una instruccion valida");
			}

		}while(opcion!=0);

	}


}
