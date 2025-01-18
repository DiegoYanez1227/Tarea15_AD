package pool;
 
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class MyDataSource {

	private static BasicDataSource ds= new BasicDataSource();
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/empresa");
		ds.setUsername("root");
		ds.setPassword("admin");
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(10);
	}
	
	public static Connection getConnection()throws SQLException{
		return ds.getConnection();
	}
	
	private MyDataSource() {}
}
