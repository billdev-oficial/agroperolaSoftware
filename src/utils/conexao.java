package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    private static final String URL = "jdbc:sqlite:data/fiado.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
