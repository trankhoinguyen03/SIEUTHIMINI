package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class connectSql {
	protected Connection conn = null;
	

	public connectSql() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QuanLySieuThi;user=sa;password=sa;encrypt=true;trustServerCertificate=true;";
		conn = DriverManager.getConnection(connectionUrl);
	}
	public void closeConnection() {
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	

}
 