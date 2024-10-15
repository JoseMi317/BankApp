package Frontend.ATMVirtual.TransaccionesPage;

import javax.swing.*;

import Frontend.ATMVirtual.MainPage.MainPage;

import java.awt.*;
import java.util.prefs.Preferences;

public class TransaccionesPage extends JFrame {
    private static JFrame frame;
    JLabel label1, label2, label3, error;
    JTextField textField;
    JButton button2, button1, button3;
    JPasswordField passwordField;

    public TransaccionesPage() {
        super("Transacciones");

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
        JTextField textField = new JTextField(15);
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
        JTextField textField2 = new JTextField(15);
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
        panel.add(button1); // Añadir al panel

        // Botón Atras
        JButton button2 = new JButton("Atras");
        button2.setForeground(Color.white);
        button2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button2.setBackground(new Color(225, 18, 18));
        button2.setBounds(20, 250, 200, 40);
        button2.addActionListener(e -> regresarCuenta());
        panel.add(button2);

        // Mostrar la ventana
        setVisible(true);
    }


    private void regresarCuenta() {
        MainPage cuenta = new MainPage();
        cuenta.setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        // Crear la ventana con el fondo
        new TransaccionesPage();
    }
}