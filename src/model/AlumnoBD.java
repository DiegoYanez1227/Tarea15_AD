package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.*;

import pool.MyDataSource;

public class AlumnoBD implements AlumnoDAO{

	
	static Logger Logger = LogManager.getLogger(AlumnoBD.class);
		

	
	
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
			Logger.error("Error al introducir el alumno dentro de la base de datos");
		}
		return result;
	}

	@Override
	public int aniadirAlumnos(List<Alumno> alumnos) {

		int result=0;
		for (Alumno alumno : alumnos) {
			aniadirAlumno(alumno);
			result ++;
		}
		Logger.info("Se ha completado con exito el añadido de todos los alumnos");
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
			Logger.error("Error al introducir el grupo dentro de la base de datos");
		}
		return result;
	}
	
	@Override
	public int aniadirGrupos(List<Grupo>grupos) {
		int result=0;
		for (Grupo grupo : grupos) {
			aniadirGrupo(grupo);
			result ++;
		}

		return result;
	}

	@Override
	public List<Alumno> obtenerTodosLosAlumnos() {
		String sql = "SELECT nia, nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo FROM alumno";

		List <Alumno> alumnos = null;

		try(Connection conexion = MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				ResultSet rs = sentencia.executeQuery()){
			alumnos= new ArrayList<>();

			Alumno alumno;

			while(rs.next()) {

				alumno= new Alumno();
				generarAlumno(rs, alumno);

				alumnos.add(alumno);
			}
		}catch(SQLException e) {
			Logger.error("Error al obtener todos los alumnos de dentro de la base de datos");
			return null;
		}
		return alumnos;
	}

	@Override
	public int modificarNombrePorNia(int nia, String nombre) {
		Alumno alumno=obtenerAlumnoPorNIA(nia);
		// Actualizar los datos en la base de datos
		String sql = "UPDATE alumnos SET nombre = ? WHERE nia = ?";

		int filasActualizadas=0;
		try(Connection conexion = MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				ResultSet rs = sentencia.executeQuery()){

			sentencia.setString(1, nombre);
			sentencia.setInt(2, nia);

			return filasActualizadas= sentencia.executeUpdate();

		} catch (SQLException e) {
			Logger.error("Error en la modificacion del nombre del alumno con nia "+nia);
			e.printStackTrace();
		}
		return filasActualizadas;
	}



	@Override
	public void eliminarPorNia(int nia) {
		String sql = "DELETE FROM alumno WHERE nia = ?";
		try(Connection conexion = MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setInt(1, nia);
			sentencia.executeQuery();

			int filasEliminadas = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.error("Error al eliminar el alumno con nia "+nia);
		}		
	}

	@Override
	public void eliminarPorCurso(String curso) {

		obtenerCursos();

		String sql = "DELETE FROM alumno WHERE curso = ?";
		try (Connection conexion = MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				ResultSet rs = sentencia.executeQuery(sql)) {

			sentencia.setString(1, curso);
			sentencia.executeQuery();

			int filasEliminadas = sentencia.executeUpdate();
		} catch (SQLException e) {
			Logger.error("Error al eliminar los alumnos que pertenecen al curso: "+curso);
		}

	}

	@Override
	public List<String> obtenerCursos() {
	    String sql = "SELECT DISTINCT curso FROM alumno";
	    List<String> cursos = new ArrayList<>();

	    try (Connection conexion = MyDataSource.getConnection();
	         PreparedStatement sentencia = conexion.prepareStatement(sql);
	         ResultSet rs = sentencia.executeQuery()) {

	        while (rs.next()) {
	            String curso = rs.getString("curso");
	            cursos.add(curso); 
	        }

	    } catch (SQLException e) {
	    	Logger.error("Error al obtener los cursos");
	        return null;
	    }
	    return cursos;
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
	
	private void generarGrupo(ResultSet rs, Grupo grupo) throws SQLException {
		grupo.setId_grupo(rs.getInt("nia"));
		grupo.setNombre(rs.getString("nombre"));
		
	}

	@Override
	public Alumno obtenerAlumnoPorNIA(int nia) {
		String sql = "SELECT nia, nombre, apellidos, fecha_nacimiento, genero, ciclo, curso, grupo FROM alumno WHERE nia= ?";

		Alumno alumno = null;

		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement sentencia = conexion.prepareStatement(sql)){

			sentencia.setInt(1, nia);
			try (ResultSet rs = sentencia.executeQuery()) {
				if (rs.next()) {
					alumno = new Alumno();
					generarAlumno(rs, alumno);
				}
			}
		}catch(SQLException e) {
			Logger.error("Error al obtener el alumno con nia "+nia);
			return null;
		}
		return alumno;

	}

	@Override
	public List<Grupo> obtenerTodosLosGrupos() {
		String sql = "SELECT id_grupo, nombre FROM grupos";

		List <Grupo> grupos = null;

		try(Connection conexion = MyDataSource.getConnection();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				ResultSet rs = sentencia.executeQuery()){
			grupos= new ArrayList<Grupo>();

			Grupo grupo;

			while(rs.next()) {
				grupo= new Grupo();
				generarGrupo(rs, grupo);

				grupos.add(grupo);
			}
		}catch(SQLException e) {
			Logger.error("Error al obtener todos los grupos");
			return null;
		}
		return grupos;
	}



}
