package Main.Java;

import Frontend.ATMVirtual.LoginPage.LoginPage;
import Main.Java.DataBase.DataBaseconnector;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        try {
            Connection connection = DataBaseconnector.getConnection();
            System.out.println("Conexión exitosa a la base de datos");

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        LoginPage.main(args);

    }
}
