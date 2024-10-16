package Backend.Class.Historial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private Connection connection;

    public Historial(Connection connection) {
        this.connection = connection;
    }

    public List<String[]> obtenerHistorial(int idCuenta) {
        List<String[]> transacciones = new ArrayList<>();
        String query = "SELECT tipo_transaccion, monto, fecha_transaccion, descripcion FROM Transacciones WHERE cuenta_id = ? ORDER BY fecha_transaccion DESC";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idCuenta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tipoTransaccion = resultSet.getString("tipo_transaccion");
                double monto = resultSet.getDouble("monto");
                String fechaTransaccion = resultSet.getString("fecha_transaccion");
                String descripcion = resultSet.getString("descripcion");

                // Agregar cada columna a la lista como un array de strings
                transacciones.add(new String[]{tipoTransaccion, String.format("%.2f", monto), fechaTransaccion, descripcion});
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el historial: " + e.getMessage());
        }

        return transacciones;
    }
}
