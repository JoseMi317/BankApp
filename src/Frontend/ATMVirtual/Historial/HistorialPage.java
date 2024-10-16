package Frontend.ATMVirtual.Historial;

import Backend.Class.Historial.Historial;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistorialPage extends JDialog {
    private Connection connection;
    private int idCuenta;

    public HistorialPage(Frame owner, Connection connection, int idCuenta) throws SQLException {
        super(owner, "Historial de Transacciones", true); // Modalidad bloqueante
        this.connection = connection;
        this.idCuenta = idCuenta;
        initUI(); // Método para inicializar la interfaz gráfica
    }

    private void initUI() {
        setSize(800, 600); // Aumentar el tamaño de la ventana
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Asegurar que se cierra correctamente

        String[] columnNames = {"Tipo de Transacción", "Monto", "Fecha", "Descripción"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        Historial historial = new Historial(connection);
        try {
            List<String[]> transacciones = historial.obtenerHistorial(idCuenta);
            if (transacciones.isEmpty()) {
                tableModel.addRow(new Object[]{"No hay transacciones para mostrar", "", "", ""});
            } else {
                for (String[] transaccion : transacciones) {
                    tableModel.addRow(transaccion); // Agregar las filas directamente
                }
            }
        } catch (Exception e) {
            tableModel.addRow(new Object[]{"Error al obtener el historial", "", "", e.getMessage()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
