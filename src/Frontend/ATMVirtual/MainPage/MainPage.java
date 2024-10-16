package Frontend.ATMVirtual.MainPage;

import Backend.Class.Login.Login;
import Frontend.ATMVirtual.Historial.HistorialPage;
import Frontend.ATMVirtual.LoginPage.LoginPage;
import Frontend.ATMVirtual.TransaccionesPage.TransaccionesPage;
import Main.Java.DataBase.DataBaseconnector;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.*;

public class MainPage extends JFrame {
    private Connection connection;
    private static JFrame frame;
    JLabel label1, label2, label3;
    JButton button1, button2, button3;
    private final Preferences prefs;
    private JLabel noCuentaValue;
    private int accountId;

    public MainPage() throws SQLException {
        prefs = Preferences.userRoot().node("ATM");
        this.accountId = prefs.getInt("accountId", -1);
        connection = DataBaseconnector.getConnection();
        initComponents();
    }

    private void initComponents() {
        setSize(850, 550);
        setLocation(450, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Imagen Fondo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/prueba.png"));
        Image i2 = i1.getImage().getScaledInstance(850, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 550);
        add(image);

        // Cuadro blanco transparente
        JPanel cuadroPanel = new JPanel();
        cuadroPanel.setLayout(null);
        cuadroPanel.setBounds(160, 100, 500, 300);
        cuadroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        cuadroPanel.setBackground(new Color(255, 255, 255, 200));
        image.add(cuadroPanel);

        // Etiqueta No. Cuenta
        JLabel noCuentaLabel = new JLabel("No. Cuenta:");
        noCuentaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        noCuentaLabel.setBounds(20, 20, 200, 30);
        cuadroPanel.add(noCuentaLabel);

        // Etiqueta Tipo de Cuenta
        JLabel tipoCuentaLabel = new JLabel("Tipo de Cuenta:");
        tipoCuentaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tipoCuentaLabel.setBounds(20, 70, 200, 30);
        cuadroPanel.add(tipoCuentaLabel);

        // Etiqueta Saldo Actual
        JLabel saldoActualLabel = new JLabel("Saldo actual:");
        saldoActualLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        saldoActualLabel.setBounds(20, 130, 200, 30);
        cuadroPanel.add(saldoActualLabel);

        // Etiquetas para mostrar datos
        noCuentaValue = new JLabel();
        noCuentaValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
        noCuentaValue.setBounds(250, 20, 200, 30);
        cuadroPanel.add(noCuentaValue);
        JLabel tipoCuentaValue = new JLabel();
        tipoCuentaValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tipoCuentaValue.setBounds(250, 70, 200, 30);
        cuadroPanel.add(tipoCuentaValue);
        JLabel saldoActualValue = new JLabel();
        saldoActualValue.setFont(new Font("Tahoma", Font.PLAIN, 20));
        saldoActualValue.setBounds(250, 130, 200, 30);
        cuadroPanel.add(saldoActualValue);

        // Botón Ver Historial (mover a la posición del botón de préstamos)
        JButton buttonHistorial = new JButton("Ver Historial");
        buttonHistorial.setForeground(Color.white);
        buttonHistorial.setFont(new Font("Tahoma", Font.PLAIN, 20));
        buttonHistorial.setBackground(new Color(48, 44, 44));
        buttonHistorial.setBounds(20, 250, 200, 40); // Posición del botón de préstamos
        buttonHistorial.addActionListener(e -> {
            try {
                abrirHistorial();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        cuadroPanel.add(buttonHistorial);

        // Botón Cerrar Sesión
        JButton buttonCerrarSesion = new JButton("Cerrar Sesión");
        buttonCerrarSesion.setForeground(Color.white);
        buttonCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
        buttonCerrarSesion.setBackground(new Color(48, 44, 44));
        buttonCerrarSesion.setBounds(600, 450, 200, 40);
        buttonCerrarSesion.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cerrar sesión?", "Confirmar cierre de sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
            }
        });
        this.add(buttonCerrarSesion);

        // Botón Realizar Transacción (dejar en su lugar)
        button1 = new JButton("Transferir");
        button1.setForeground(Color.white);
        button1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button1.setBackground(new Color(48, 44, 44));
        button1.setBounds(250, 250, 200, 40);
        button1.addActionListener(e -> abrirTransacciones());
        cuadroPanel.add(button1);

        // Botón Salir (en la esquina superior derecha)
        button3 = new JButton("Salir");
        button3.setForeground(Color.red);
        button3.setFont(new Font("Tahoma", Font.PLAIN, 21));
        button3.setBackground(new Color(17, 204, 149));
        button3.setBounds(725, 10, 100, 40);
        button3.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        image.add(button3);

        // Actualizar título de la ventana con usuario
        setTitle("Cuenta Bancaria de " + prefs.get("user", ""));

        // Cargar datos de la cuenta
        cargarDatosCuenta(noCuentaValue, tipoCuentaValue, saldoActualValue);
    }

    private void cargarDatosCuenta(JLabel noCuentaLabel, JLabel tipoCuentaLabel, JLabel saldoActualLabel) {
        String usuario = prefs.get("user", "");
        Login cuenta = new Login(connection);
        String[] datos = cuenta.obtenerDatosCuenta(usuario);
        if (datos[0] != null) {
            noCuentaLabel.setText(datos[0]); // Mostrar el número de cuenta en la UI
            tipoCuentaLabel.setText(datos[1]);
            saldoActualLabel.setText("Q. " + datos[2]);
        } else {
            noCuentaLabel.setText("No disponible");
            tipoCuentaLabel.setText("No disponible");
            saldoActualLabel.setText("No disponible");
        }
    }

    private void abrirTransacciones() {
        String noCuenta = noCuentaValue.getText();
        int numeroCuenta = Integer.parseInt(noCuenta);
        TransaccionesPage transacciones = new TransaccionesPage(numeroCuenta);
        transacciones.setVisible(true);
        setVisible(false);
    }

    private void abrirHistorial() throws SQLException {
        HistorialPage historial = new HistorialPage(this, connection, accountId);
        historial.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        MainPage ventana = new MainPage();
        ventana.setVisible(true);
    }
}
