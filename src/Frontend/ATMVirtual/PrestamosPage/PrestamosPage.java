package Frontend.ATMVirtual.PrestamosPage;

import javax.swing.*;

import Frontend.ATMVirtual.MainPage.MainPage;

import java.awt.*;
import java.sql.SQLException;

public class PrestamosPage extends JFrame {
    private static JFrame ventana;
    JLabel etiquetaCuentaDestino, etiquetaMonto, etiquetaComentarios, etiquetaError;
    JTextField campoTextoCuenta, campoTextoMonto, JPasswordField;
    JButton botonTransferir, botonSalir, botonExtra;

    public PrestamosPage() {
        super("Prestamo");

        setSize(850, 550); // Tamaño de la ventana
        setLocation(320, 150); // Ubicación de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // JLayeredPane capas
        JLayeredPane panelCapas = new JLayeredPane();
        panelCapas.setBounds(0, 0, 850, 550);
        add(panelCapas);

        // Imagen de fondo
        ImageIcon imagenFondoIcono = new ImageIcon(ClassLoader.getSystemResource("img/FondoT.jpg"));
        Image imagenFondo = imagenFondoIcono.getImage().getScaledInstance(850, 550, Image.SCALE_DEFAULT);
        ImageIcon imagenEscalada = new ImageIcon(imagenFondo);
        JLabel etiquetaImagenFondo = new JLabel(imagenEscalada);
        etiquetaImagenFondo.setBounds(0, 0, 850, 550);
        panelCapas.add(etiquetaImagenFondo, Integer.valueOf(1)); // Añadir la imagen de fondo a la capa más baja

        // Cuadro gris (panel transparente)
        JPanel panelTransparente = new JPanel();
        panelTransparente.setLayout(null);
        panelTransparente.setBounds(150, 100, 500, 300);
        panelTransparente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelTransparente.setBackground(new Color(255, 255, 255, 200)); // Transparente
        panelCapas.add(panelTransparente, Integer.valueOf(2)); // Añadir panel a una capa superior

        // Monto de prestamo
        JLabel etiquetaCuentaDestino = new JLabel("Monto de Prestamo:");
        etiquetaCuentaDestino.setFont(new Font("Tahoma", Font.PLAIN, 20));
        etiquetaCuentaDestino.setBounds(10, 10, 190, 30);
        panelTransparente.add(etiquetaCuentaDestino);

        // Agregar el monto del prestamo
        JTextField campoTextoCuenta = new JTextField(15);
        campoTextoCuenta.setBackground(Color.lightGray);
        campoTextoCuenta.setForeground(Color.black);
        campoTextoCuenta.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        campoTextoCuenta.setBounds(240, 10, 230, 30);
        campoTextoCuenta.setFont(new Font("Arial", Font.BOLD, 14));
        panelTransparente.add(campoTextoCuenta); // Añadir al panel

        // Descripcion del prestamo
        JLabel etiquetaMonto = new JLabel("Descripcion:");
        etiquetaMonto.setFont(new Font("Tahoma", Font.PLAIN, 20));
        etiquetaMonto.setBounds(50, 70, 190, 30);
        panelTransparente.add(etiquetaMonto);

        // Agregar la descripcion del prestamo
        JTextField campoTextoMonto = new JTextField(15);
        campoTextoMonto.setBackground(Color.lightGray);
        campoTextoMonto.setForeground(Color.black);
        campoTextoMonto.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        campoTextoMonto.setBounds(240, 70, 230, 80);
        campoTextoMonto.setFont(new Font("Arial", Font.BOLD, 14));
        panelTransparente.add(campoTextoMonto); // Añadir al panel

        // Etiqueta contraseña
        etiquetaComentarios = new JLabel("Contraseña:");
        etiquetaComentarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
        etiquetaComentarios.setBounds(40, 160, 150, 30);
        etiquetaComentarios.setBackground(new Color(255, 255, 255, 200));
        panelTransparente.add(etiquetaComentarios);

        // Cuadro solicitud contraseña
        JPasswordField = new JPasswordField(15);
        JPasswordField.setBackground(Color.lightGray);
        JPasswordField.setForeground(Color.black);
        JPasswordField.setBounds(350, 265, 230, 30);
        JPasswordField.setFont(new Font("Arial", Font.BOLD, 14));
        add(JPasswordField);

        // Botón Solicitar
        JButton botonTransferir = new JButton("Solicitar");
        botonTransferir.setForeground(Color.white);
        botonTransferir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        botonTransferir.setBackground(new Color(48, 44, 44));
        botonTransferir.setBounds(240, 250, 200, 40);
        panelTransparente.add(botonTransferir); // Añadir al panel

        // Botón Atras
        JButton botonSalir = new JButton("Atras");
        botonSalir.setForeground(Color.white);
        botonSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        botonSalir.setBackground(new Color(225, 18, 18));
        botonSalir.setBounds(20, 250, 200, 40);
        botonSalir.addActionListener(e -> {
            try {
                regresarCuenta();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        panelTransparente.add(botonSalir);

        // Mostrar la ventana
        setVisible(true);
    }

    private void regresarCuenta() throws SQLException {
        MainPage cuenta = new MainPage();
        cuenta.setVisible(true);
        setVisible(false);
    }


    public static void main(String[] args) {
        // Crear la ventana con el fondo
        new PrestamosPage();
    }
}

