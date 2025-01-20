package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pool.MyDataSource;

public class AlumnoBD implements AlumnoDAO{

	@Override
	public int aniadirAlumno(Alumno alumno) {
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
			sentencia.setInt(5, alumno.getGrupo());

			return result= sentencia.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int aniadirGrupo(Grupo grupo) {
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
	public List<Alumno> obtenerTodosLosAlumnos() {
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
	public int modificarNombrePorNia(int nia) {
		Alumno alumno=obtenerAlumno(nia);
					// Actualizar los datos en la base de datos
		String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, fechaNacimiento = ?, genero = ?, ciclo = ?, curso = ?, grupo = ? WHERE nia = ?";
		
		int filasActualizadas=0;
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			ResultSet rs = sentencia.executeQuery()){
			
			sentencia.setString(1, alumno.getNombre());
			sentencia.setString(2, alumno.getApellidos());
			sentencia.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
			sentencia.setString(4, String.valueOf(alumno.getGenero()));
			sentencia.setString(5, alumno.getCiclo());
			sentencia.setString(6, alumno.getCurso());
			sentencia.setInt(7, alumno.getGrupo());
			sentencia.setInt(8, nia);

			filasActualizadas = sentencia.executeUpdate();
			if (filasActualizadas > 0) {
				System.out.println("Alumno actualizado correctamente.");
			} else {
				System.out.println("Error inesperado al actualizar el alumno.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filasActualizadas;
	}
	
	
	

	@Override
	public void eliminarPorNia(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPorCurso(String curso) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Alumno obtenerAlumno(int nia) {
		
		Alumno result = null;

		String sql = "SELECT nia, nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo FROM alumno where nia=?";
		try (Connection conexion = MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setInt(1, nia);

			try(ResultSet rs = sentencia.executeQuery()){
				
				//usamos el if porque solo obtendremos un dato
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

	private void generarAlumno(ResultSet rs, Alumno alumno) throws SQLException {
		alumno.setNia(rs.getInt("nia"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setApellidos(rs.getString("apellidos"));
		alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
		String genero = rs.getString("genero");
		alumno.setGenero(genero.charAt(0));
		alumno.setCiclo(rs.getString("ciclo"));
		alumno.setCurso(rs.getString("curso"));
		alumno.setGrupo(Integer.parseInt(rs.getString("grupo")));
	}

}
