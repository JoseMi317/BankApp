package Main.Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.Java.DataBase.DataBaseconnector;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = DataBaseconnector.getConnection();
            System.out.println("Conexión exitosa a la base de datos");

            // Consultar y mostrar los datos de los clientes
            String selectQuery = "SELECT * FROM Clientes";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = selectStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellidoPaterno = resultSet.getString("apellido_paterno");
                    String apellidoMaterno = resultSet.getString("apellido_materno");
                    System.out.printf("ID: %d, Nombre: %s %s %s%n", id, nombre, apellidoPaterno, apellidoMaterno);
                }
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
