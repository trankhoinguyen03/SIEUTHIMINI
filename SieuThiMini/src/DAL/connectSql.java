package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectSql {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SIEUTHIMINI;user=sa;password=sa;encrypt=true;trustServerCertificate=true;";

    protected Connection conn = null;

    public connectSql() throws SQLException {
        conn = getConnection();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
