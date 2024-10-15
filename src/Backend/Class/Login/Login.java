package Backend.Class.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private Connection connection;

    public Login(Connection connection) {
        this.connection = connection;
    }

    public String[] obtenerDatosCuenta(String nombre_usuario) {
        String[] datos = new String[3];
        String query = "SELECT numero_cuenta, tipo_cuenta, saldo FROM Cuentas WHERE nombre_usuario = ?"; // Asegúrate de tener una tabla 'cuentas' con estas columnas

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombre_usuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datos[0] = rs.getString("numero_cuenta");
                datos[1] = rs.getString("tipo_cuenta");
                datos[2] = rs.getString("saldo");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Maneja adecuadamente las excepciones
        }

        return datos; // Devuelve los datos obtenidos
    }
}
