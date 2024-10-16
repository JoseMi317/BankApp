package Backend.Class.Transacciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Transaccions {
    private Connection connection;

    public Transaccions(Connection connection) {
        this.connection = connection;
    }

    public void realizarTransaccion(int cuentaOrigen, int cuentaDestino, double monto) throws SQLException {
        try {
            // Iniciar transacción
            connection.setAutoCommit(false);
    
            // Verificar saldo suficiente
            String sqlSaldo = "SELECT saldo FROM Cuentas WHERE numero_cuenta = ?";
            try (PreparedStatement saldoStatement = connection.prepareStatement(sqlSaldo)) {
                saldoStatement.setString(1, Integer.toString(cuentaOrigen)); // Se supone que numero_cuenta es VARCHAR(20)
                ResultSet rs = saldoStatement.executeQuery();
                if (rs.next()) {
                    double saldoOrigen = rs.getDouble("saldo");
                    if (saldoOrigen < monto) {
                        throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen.");
                    }
                } else {
                    throw new IllegalArgumentException("La cuenta de origen no existe.");
                }
            }
    
            // Debitar de la cuenta origen
            PreparedStatement debitar = connection.prepareStatement("UPDATE Cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?");
            debitar.setDouble(1, monto);
            debitar.setString(2, Integer.toString(cuentaOrigen)); // Se supone que numero_cuenta es VARCHAR(20)
            debitar.executeUpdate();
    
            // Acreditar a la cuenta destino
            PreparedStatement acreditar = connection.prepareStatement("UPDATE Cuentas SET saldo = saldo + ? WHERE numero_cuenta = ?");
            acreditar.setDouble(1, monto);
            acreditar.setString(2, Integer.toString(cuentaDestino)); // Se supone que numero_cuenta es VARCHAR(20)
            acreditar.executeUpdate();
    
            // Confirmar transacción
            connection.commit();
            JOptionPane.showMessageDialog(null, "Transferencia exitosa", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Error al realizar la transacción: " + e.getMessage());
            throw e; // Vuelve a lanzar la excepción después de rollback
        } catch (IllegalArgumentException e) {
            connection.rollback();
            System.out.println("Error: " + e.getMessage());
            throw e; // Lanza excepción para manejar en el frontend
        } finally {
            connection.setAutoCommit(true); // Volver a activar autocommit
        }
    }
}
