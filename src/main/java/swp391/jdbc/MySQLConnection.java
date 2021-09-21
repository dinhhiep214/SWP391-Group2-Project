package swp391.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    private final String serverName = "localhost";
    private final String dbName = "swp391_g2_project";
    private final String portNumber = "3306";
    private final String instance="";
    private final String userID = "root";
    private final String password = "1234";

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://"+serverName+":"+portNumber + "/" +dbName;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, userID, password);
    }
}
