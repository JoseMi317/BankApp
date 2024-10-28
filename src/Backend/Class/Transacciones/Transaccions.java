package Backend.Class.Transacciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Transaccions {
    @SuppressWarnings("FieldMayBeFinal")
    private Connection connection;

    public Transaccions(Connection connection) {
        this.connection = connection;
    }

    public void realizarTransaccion(int cuentaOrigen, int cuentaDestino, double monto) throws SQLException {
        try {
            // Iniciar transacción
            connection.setAutoCommit(false);
    
            // Obtener los IDs de las cuentas a partir de los números de cuenta
            String sqlIdCuenta = "SELECT id FROM Cuentas WHERE numero_cuenta = ?";
            
            int idCuentaOrigen;
            int idCuentaDestino;
            
            // Obtener el id de la cuenta origen
            try (PreparedStatement stmtOrigen = connection.prepareStatement(sqlIdCuenta)) {
                stmtOrigen.setString(1, Integer.toString(cuentaOrigen));
                ResultSet rsOrigen = stmtOrigen.executeQuery();
                if (rsOrigen.next()) {
                    idCuentaOrigen = rsOrigen.getInt("id");
                } else {
                    throw new IllegalArgumentException("La cuenta de origen no existe.");
                }
            }
    
            // Obtener el id de la cuenta destino
            try (PreparedStatement stmtDestino = connection.prepareStatement(sqlIdCuenta)) {
                stmtDestino.setString(1, Integer.toString(cuentaDestino));
                ResultSet rsDestino = stmtDestino.executeQuery();
                if (rsDestino.next()) {
                    idCuentaDestino = rsDestino.getInt("id");
                } else {
                    throw new IllegalArgumentException("La cuenta de destino no existe.");
                }
            }
    
            // Verificar saldo suficiente
            String sqlSaldo = "SELECT saldo FROM Cuentas WHERE numero_cuenta = ?";
            try (PreparedStatement saldoStatement = connection.prepareStatement(sqlSaldo)) {
                saldoStatement.setString(1, Integer.toString(cuentaOrigen));
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
            PreparedStatement debitar = connection.prepareStatement(
                "UPDATE Cuentas SET saldo = saldo - ? WHERE numero_cuenta = ?");
            debitar.setDouble(1, monto);
            debitar.setString(2, Integer.toString(cuentaOrigen));
            debitar.executeUpdate();
    
            // Acreditar a la cuenta destino
            PreparedStatement acreditar = connection.prepareStatement(
                "UPDATE Cuentas SET saldo = saldo + ? WHERE numero_cuenta = ?");
            acreditar.setDouble(1, monto);
            acreditar.setString(2, Integer.toString(cuentaDestino));
            acreditar.executeUpdate();
    
            // Insertar registro en la tabla Transacciones para la cuenta origen
            String sqlInsertOrigen = "INSERT INTO Transacciones (cuenta_id, tipo_transaccion, monto, fecha_transaccion, descripcion) "
                                   + "VALUES (?, ?, ?, NOW(), ?)";
            try (PreparedStatement insertOrigen = connection.prepareStatement(sqlInsertOrigen)) {
                insertOrigen.setInt(1, idCuentaOrigen);
                insertOrigen.setString(2, "Débito");
                insertOrigen.setDouble(3, monto);
                insertOrigen.setString(4, "Transferencia a cuenta " + cuentaDestino);
                insertOrigen.executeUpdate();
            }
    
            // Insertar registro en la tabla Transacciones para la cuenta destino
            String sqlInsertDestino = "INSERT INTO Transacciones (cuenta_id, tipo_transaccion, monto, fecha_transaccion, descripcion) "
                                    + "VALUES (?, ?, ?, NOW(), ?)";
            try (PreparedStatement insertDestino = connection.prepareStatement(sqlInsertDestino)) {
                insertDestino.setInt(1, idCuentaDestino);
                insertDestino.setString(2, "Crédito");
                insertDestino.setDouble(3, monto);
                insertDestino.setString(4, "Transferencia desde cuenta " + cuentaOrigen);
                insertDestino.executeUpdate();
            }
    
            // Confirmar transacción
            connection.commit();
            JOptionPane.showMessageDialog(null, "Transferencia exitosa", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Error al realizar la transacción: " + e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            connection.rollback();
            System.out.println("Error: " + e.getMessage());
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}    
