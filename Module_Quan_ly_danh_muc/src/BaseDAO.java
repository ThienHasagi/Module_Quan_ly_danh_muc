package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    protected Connection connection;

    // Chuỗi kết nối trực tiếp đến hệ quản trị cơ sở dữ liệu Microsoft SQL Server
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QLXuatNhapHang;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "your_password";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbURL, username, password);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
