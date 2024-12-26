package LBAJXLibrariesV1.connection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DBConnect {
    public Connection conn;
    public Statement stat;
    public ResultSet result;
    public PreparedStatement pstat;

    // Inject properties from application.properties
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public DBConnect() {
        try {
            // Establish the connection
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-M1TAU7S:1433;databaseName=devlba;encrypt=true;trustServerCertificate=true", "sa", "polman");
            stat = conn.createStatement();
            System.out.println("Connection Successful: " + stat);
        } catch (Exception e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}