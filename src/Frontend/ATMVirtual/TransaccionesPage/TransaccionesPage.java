package Frontend.ATMVirtual.TransaccionesPage;

import Backend.Class.Security.Security;
import Backend.Class.Transacciones.Transaccions;
import Frontend.ATMVirtual.MainPage.MainPage;
import Main.Java.DataBase.DataBaseconnector;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class TransaccionesPage extends JFrame {

    JLabel label3;
    JTextField textField;
    JTextField textField2; 
    JButton button2;
    @SuppressWarnings("FieldMayBeFinal")
    private int numeroCuenta;

    public TransaccionesPage(int numeroCuenta) {
        super("Transacciones");
        this.numeroCuenta = numeroCuenta; // Guardar el número de cuenta

        setSize(850, 550); // Tamaño de la ventana
        setLocation(320, 150); // Ubicación de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // JLayeredPane capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 850, 550);
        add(layeredPane);

        // Imagen de fondo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/FondoT.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 850, 550);
        layeredPane.add(image, Integer.valueOf(1)); // Añadir la imagen de fondo a la capa más baja

        // Cuadro gris (panel transparente)
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(150, 100, 500, 300);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(new Color(255, 255, 255, 200)); // Transparente
        layeredPane.add(panel, Integer.valueOf(2)); // Añadir panel a una capa superior

        // Cuenta Destino
        JLabel lblNewLabel = new JLabel("Cuenta destino:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(10, 10, 150, 30);
        panel.add(lblNewLabel);

        // Agregar cuenta
        textField = new JTextField(15); // Asignar a la variable de instancia
        textField.setBackground(Color.lightGray);
        textField.setForeground(Color.black);
        textField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        textField.setBounds(165, 10, 230, 30);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textField); // Añadir al panel

        // Monto de depósito
        JLabel lblNewLabel2 = new JLabel("Monto:");
        lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel2.setBounds(50, 70, 150, 30);
        panel.add(lblNewLabel2);

        // Agregar monto a depositar
        textField2 = new JTextField(15); // Asignar a la variable de instancia
        textField2.setBackground(Color.lightGray);
        textField2.setForeground(Color.black);
        textField2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        textField2.setBounds(165, 70, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textField2); // Añadir al panel

        // Etiqueta comentarios
        label3 = new JLabel("Comentarios:");
        label3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label3.setBounds(40, 120, 150, 30);
        label3.setBackground(new Color(255, 255, 255, 200));
        panel.add(label3);

        // Cuadro comentarios
        JTextField textField3 = new JTextField(15);
        textField3.setBackground(Color.lightGray);
        textField3.setForeground(Color.black);
        textField3.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        textField3.setBounds(10, 150, 450, 70);
        textField3.setFont(new Font("Arial", Font.BOLD, 10));
        panel.add(textField3); // Añadir al panel

        // Botón Transferir ahora
        JButton button1 = new JButton("Transferir Ahora");
        button1.setForeground(Color.white);
        button1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button1.setBackground(new Color(48, 44, 44));
        button1.setBounds(250, 250, 200, 40);
        button1.addActionListener(e -> {
            realizarTransaccion(); // Llama al método de transacción
        });
        panel.add(button1); // Añadir al panel

        // Botón Atras
        button2 = new JButton("Atras");
        button2.setForeground(Color.white);
        button2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button2.setBackground(new Color(225, 18, 18));
        button2.setBounds(20, 250, 200, 40);
        button2.addActionListener(e -> {
            try {
                regresarCuenta();
            } catch (SQLException e1) {
            }
        });
        panel.add(button2);

        // Mostrar la ventana
        setVisible(true);
    }

    private void realizarTransaccion() {
        // Obtener los datos de la interfaz (como cuenta destino y monto)
        try {
            int cuentaDestino = Integer.parseInt(textField.getText().trim()); // Obtener el texto del JTextField
            double monto = Double.parseDouble(textField2.getText().trim()); // Obtener el texto del JTextField

            // Crear una instancia de Transaccions y realizar la transacción
            Transaccions transacciones = new Transaccions(DataBaseconnector.getConnection());
            transacciones.realizarTransaccion(numeroCuenta, cuentaDestino, monto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores válidos para cuenta destino y monto.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
        }
    }

    private void regresarCuenta() throws SQLException {
        MainPage cuenta = new MainPage();
        cuenta.setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        Security security = new Security();
        String user = "nombreUsuario"; 
        String accountNumber = security.getAccountNumber(user);
        new TransaccionesPage(Integer.parseInt(accountNumber));
    }
}
