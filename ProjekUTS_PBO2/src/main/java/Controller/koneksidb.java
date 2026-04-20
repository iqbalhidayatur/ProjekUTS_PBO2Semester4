
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class koneksidb {
    public static Connection getConnection() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/db_uts";
            String user = "root";
            String pass = "";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}