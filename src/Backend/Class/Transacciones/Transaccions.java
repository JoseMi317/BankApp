package Backend.Class.Transacciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaccions {
    private Connection connection;

    public Transaccions(Connection connection) {
        this.connection = connection;
    }

    public void realizarTransaccion(int cuentaOrigen, int cuentaDestino, double monto) throws SQLException {
        try {
            // Iniciar transacción
            connection.setAutoCommit(false);

            // Debitar de la cuenta origen
            PreparedStatement debitar = connection.prepareStatement("UPDATE Cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?");
            debitar.setDouble(1, monto);
            debitar.setInt(2, cuentaOrigen);
            debitar.executeUpdate();

            // Acreditar a la cuenta destino
            PreparedStatement acreditar = connection.prepareStatement("UPDATE Cuentas SET saldo = saldo + ? WHERE numero_cuenta = ?");
            acreditar.setDouble(1, monto);
            acreditar.setInt(2, cuentaDestino);
            acreditar.executeUpdate();

            // Confirmar transacción
            connection.commit();

            System.out.println("Transacción realizada exitosamente");
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Error al realizar la transacción: " + e.getMessage());
        }
    }
}
