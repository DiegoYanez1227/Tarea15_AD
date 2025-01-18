package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alumno;
import model.Grupo;
import pool.MyDataSource;

public class AlumnoDAOImpl implements AlumnoDAO{

	private  final String ARCHIVOBINARIO = "C:\\Users\\usuario\\git\\Tarea8_AD\\src\\archivos\\alumnos.dat";
	private  final String ARCHIVOJSON = "C:\\Users\\usuario\\git\\Tarea8_AD\\src\\archivos\\alumnos.json";
	
	private static AlumnoDAOImpl instance;

	static  {
		instance= new AlumnoDAOImpl();
	}

	private AlumnoDAOImpl() {};


	public static AlumnoDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public int aniadirAlumno(Alumno alumno){

		String sql="""
				INSERT INTO alumno (nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo)
				VALUES(?, ?, ?, ?, ?, ?, ?);
				""";
		int result=0;

		try(Connection conexion= MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setString(1, alumno.getNombre());
			sentencia.setString(2, alumno.getApellidos());
			sentencia.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
	        sentencia.setString(4, String.valueOf(alumno.getGenero()));
			sentencia.setString(5, alumno.getCiclo());
			sentencia.setString(4, alumno.getCurso());
			sentencia.setString(5, alumno.getGrupo());

			return result= sentencia.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int aniadirGrupo(Grupo grupo){

		String sql="""
				INSERT INTO grupo (nombre)
				VALUES(?);
				""";
		int result=0;

		try(Connection conexion= MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setString(1, grupo.getNombre());
			

			return result= sentencia.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Alumno getById(int id){

		Alumno result = null;

		String sql ="SELECT nia, nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo FROM alumno where nia=?";

		try (Connection conexion = MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setInt(1, id);

			try(ResultSet rs = sentencia.executeQuery()){
				
				//usame el if porque solo obtendremos un dato
				if(rs.next()) {
					result= new Alumno();
					generarAlumno(rs, result);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	
	
	@Override
	public List<Alumno> obtenerTodosLosAlumnos(){
		String sql = "SELECT nia, nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo FROM alumno";

		List <Alumno> alumnos = new ArrayList<>();

		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet rs = sentencia.executeQuery()){


			Alumno alumno;

			while(rs.next()) {

				alumno= new Alumno();
				generarAlumno(rs, alumno);

				alumnos.add(alumno);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return alumnos;
	}


	
	
	
	@Override
	public int update(Alumno alumno){

		String sql= """
				
				UPDATE alumno SET nombre=?, apellidos=?, fecha_nacimiento=?, genero=?, ciclo=?, curso=?, grupo=?
				WHERE nia=?
				""";
		
		int result = 0;
		try(Connection conexion= MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setString(1, alumno.getNombre());
			sentencia.setString(2, alumno.getApellidos());
			sentencia.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
			sentencia.setLong(4, alumno.getGenero());
			sentencia.setString(5, alumno.getCiclo());
			sentencia.setString(5, alumno.getCurso());
			sentencia.setString(5, alumno.getGrupo());
			sentencia.setInt (6, alumno.getNia());

			return result= sentencia.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	@Override
	public void delete(int id){
		
		String sql= "DELETE FROM alumno WHERE nia=? ";
		
		try(Connection conexion =MyDataSource.getConnection();
			PreparedStatement sentencia= conexion.prepareStatement(sql)){
			
			sentencia.setInt(1, id);
			sentencia.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	

	private void generarAlumno(ResultSet rs, Alumno alumno) throws SQLException {
		alumno.setNia(rs.getInt("nia"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setApellidos(rs.getString("apellidos"));
		alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
		String genero = rs.getString("genero");
		alumno.setGenero(genero.charAt(0));
		alumno.setCiclo(rs.getString("ciclo"));
		alumno.setCurso(rs.getString("curso"));
		alumno.setGrupo(rs.getString("grupo"));
	}


	@Override
	public String guardarAlumnoBinario(List<Alumno> alumnos) {
		//Lógica para guardar alumnos en un fichero Binario
		String mensaje="";
		System.out.println("Funcionalidad para guardar alumnos en un fichero binario.");
		if(!alumnos.isEmpty()) {
			FileOutputStream fos=null;
			ObjectOutputStream oos=null;

			try {
				fos=new FileOutputStream(new File(ARCHIVOBINARIO));
				oos= new ObjectOutputStream(fos);

				for (Alumno alumno : alumnos) {
					oos.writeObject(alumno);
				}
				mensaje= "Se han guardado correctamente";
			} catch (IOException e) {
				e.printStackTrace();
				mensaje= "No se ha podido guardar los alumnos";
			}finally {
				try {
					if(oos!=null)
						oos.close();
					if(fos!=null)
						fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			mensaje= "No hay alumnos a guardar";
		}
		return mensaje;
	}


	@Override
	public void leerAlumnoJSON() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int modificarNombrePorNia(int nia) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void eliminarPorNia(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarPorCurso(String curso) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void guardarGrupoJSON(List<Grupo> grupos) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void leerGrupoJSON() {
		// TODO Auto-generated method stub
		
	}
}
