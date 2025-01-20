package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class AlumnoFichero implements AlumnoDAO{

	private final String FICHEROBINARIO = "C:\\Users\\diego\\Desktop\\eclipse_AD\\Tarea15\\src\\model\\alumnos.bin";

	@Override
	public int aniadirAlumno(Alumno alumno) {

		int result = 0;

		try (FileOutputStream fileOut = new FileOutputStream(FICHEROBINARIO, true);
				ObjectOutputStream objOut = new AppendingObjectOutputStream(fileOut)) {

			objOut.writeObject(alumno);
			result = 1; // Indicar éxito en la escritura
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public int aniadirGrupo(Grupo grupo) {

		int result = 0;

		try (FileOutputStream fileOut = new FileOutputStream(FICHEROBINARIO, true);
				ObjectOutputStream objOut = new AppendingObjectOutputStream(fileOut)) {

			objOut.writeObject(grupo);
			result = 1; // Indicar éxito en la escritura
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Alumno> obtenerTodosLosAlumnos() {

		List<Alumno> alumnos = new ArrayList<>();

		try (FileInputStream fileIn = new FileInputStream(FICHEROBINARIO);
				ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

			while (true) {
				try {
					Alumno alumno = (Alumno) objIn.readObject();
					alumnos.add(alumno);
					// System.out.println(alumno);
					return alumnos;
				} catch (IOException e) {
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return alumnos;
	}

	@Override
	public int modificarNombrePorNia(int nia, String nombre) {

		List<Alumno> alumnos = new ArrayList<>();
		boolean encontrado = false;

		// Leer todos los alumnos del fichero
		try (FileInputStream fileIn = new FileInputStream(FICHEROBINARIO);
				ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

			while (true) {
				try {
					Alumno alumno = (Alumno) objIn.readObject();
					if (alumno.getNia() == nia) {
						alumno.setNombre(nombre); 
						alumnos.add(alumno); 
						encontrado = true;
					} else {
						alumnos.add(alumno); 
					}
				} catch (IOException e) {
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		int modificado=0;
		// Sobrescribir el fichero con los alumnos actualizados
		if (encontrado) {
			try (FileOutputStream fileOut = new FileOutputStream(FICHEROBINARIO);
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

				for (Alumno alumno : alumnos) {
					objOut.writeObject(alumno);
				}
				modificado=1;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return modificado;
	}

	@Override
	public void eliminarPorNia(int nia) {

		List<Alumno> alumnos = new ArrayList<>();
		boolean eliminado = false;

		// Leer todos los alumnos del fichero
		try (FileInputStream fileIn = new FileInputStream(FICHEROBINARIO);
				ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

			while (true) {
				try {
					Alumno alumno = (Alumno) objIn.readObject();
					if (alumno.getNia() != nia) {
						alumnos.add(alumno); 
					} else {
						eliminado = true; 
					}
				} catch (IOException e) {
					
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Sobrescribir el fichero con los alumnos actualizados
		if (eliminado) {
			try (FileOutputStream fileOut = new FileOutputStream(FICHEROBINARIO);
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

				for (Alumno alumno : alumnos) {
					objOut.writeObject(alumno);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}



	@Override
	public void eliminarPorCurso(int curso) {
		// TODO Auto-generated method stub

	}



}
class AppendingObjectOutputStream extends ObjectOutputStream {
	public AppendingObjectOutputStream(FileOutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// No escribimos un nuevo encabezado si el archivo ya existe
		reset();
	}
}
