package Frontend.ATMVirtual.LoginPage;
import javax.swing.*;

import Frontend.ATMVirtual.MainPage.MainPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

public class LoginPage extends JFrame {
    private static JFrame frame;
    JLabel label1, label2, label3, error;
    JTextField textField;
    JPasswordField passwordField1;
    JButton button2, button1, button3;
    private final Preferences prefs;

    // El constructor
    public LoginPage() {
        super("Autenticacion");
        prefs = Preferences.userRoot().node("ATM");

        setSize(850, 550); // Tamaño de la ventana
        setLocation(320, 150); // ubicación de la ventana

        // Imagen de fondo
        ImageIcon iii1 = new ImageIcon(ClassLoader.getSystemResource("img/backbg.png"));
        Image iii2 = iii1.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
        ImageIcon iii3 = new ImageIcon(iii2);
        JLabel iiimage = new JLabel(iii3);
        iiimage.setBounds(0, 0, getWidth(), getHeight());
        add(iiimage);

        // 1ra Imagen (Imagen del banco)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100, 100);
        add(image);

        // 2da imagen (Imagen de la tarjeta)
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("img/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(625, 300, 200, 200);
        add(iimage);

        // Etiqueta1 Bienvenida
        JLabel Label1 = new JLabel("Bienvenido a nuestro ATM", JLabel.CENTER);
        Label1.setForeground(Color.white);
        Label1.setFont(new Font("AvantGarde", Font.BOLD, 16));
        Label1.setBounds(300, 120, 200, 30);
        add(Label1);

        // Etiqueta2 Usuario
        JLabel Label2 = new JLabel("Usuario:", JLabel.CENTER);
        Label2.setForeground(Color.white);
        Label2.setFont(new Font("AvantGarde", Font.BOLD, 16));
        Label2.setBounds(115, 200, 200, 30);
        add(Label2);

        // Etiqueta3 Contraseña
        JLabel Label3 = new JLabel("Contraseña:", JLabel.CENTER);
        Label3.setForeground(Color.white);
        Label3.setFont(new Font("AvantGarde", Font.BOLD, 16));  // Corregido el nombre de la fuente
        Label3.setBounds(100, 300, 200, 30);
        add(Label3);

        // Ingreso texto etiqueta 1 (Usuario)
        textField = new JTextField(15);
        textField.setBounds(260, 200, 230, 30);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField);

        // Ingreso texto etiqueta 2 (Contraseña)
        passwordField1 = new JPasswordField(15);
        passwordField1.setBounds(260, 300, 230, 30);
        passwordField1.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField1);

        //Etiqueta de errores
        error = new JLabel("Usuario o contraseña incorrecto", JLabel.CENTER);
        error.setForeground(Color.red);
        error.setFont(new Font("AvantGarde", Font.BOLD, 16));  // Corregido el nombre de la fuente
        error.setBounds(250, 340, 300, 30);
        error.setVisible(false);
        add(error);

        // Botón Ingresar
        button1 = new JButton("Ingresar");
        button1.setBackground(Color.yellow);
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.BLACK);
        button1.setBounds(400, 390, 100, 40);
        button1.addActionListener((ActionEvent e) -> loginBtnClicked(textField.getText(), passwordField1.getPassword()));
        add(button1);

        // Botón Limpiar
        button2 = new JButton("Limpiar");  // Cambié el texto del segundo botón a "Limpiar"
        button2.setBackground(Color.yellow);
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.BLACK);
        button2.setBounds(280, 390, 100, 40);  // Cambié la posición para que no esté encima del botón "Ingresar"
        button2.addActionListener((ActionEvent e) -> cleanBtnClicked());
        add(button2);

        // layout null para usar bounds
        setLayout(null);

        // El fondo quede atrás
        getContentPane().add(iiimage, new Integer(Integer.MIN_VALUE));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra
        setVisible(true); // Hace visible la ventana
    }

    private void loginBtnClicked(String user, char[] password)
    {
        if(user.isEmpty() || password.length == 0) {
            error.setVisible(true);
            return;
        }
        System.out.format("Name: %s, Occupation: %s%n", user, String.valueOf(password));

        //Llamar/consultar base de datos para validar el usuario ingresado y luego obtener el nombre del usuario
        prefs.put("user", user);
        frame.setVisible(false);
        final MainPage cuenta = new MainPage();
        cuenta.setVisible(true);
    }

    private void cleanBtnClicked()
    {
        error.setVisible(false);
        textField.setText("");
        passwordField1.setText("");
    }

    public static void main(String[] args) {
        frame = new LoginPage();
    }

    
}

