package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conexao;

    public static Properties getPropriedades() throws IOException {
        Properties propriedades = new Properties();
        FileInputStream arquivo = new FileInputStream("./propriedade/propriedade.properties");
        propriedades.load(arquivo);
        return propriedades;
    }

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            try {
                Properties p = DB.getPropriedades();
                conexao = DriverManager.getConnection(p.getProperty("host"), p.getProperty("user"), p.getProperty("password"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void fechaConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("\nConexão fechada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}