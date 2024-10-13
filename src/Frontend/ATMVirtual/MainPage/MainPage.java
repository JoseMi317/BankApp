package Frontend.ATMVirtual.MainPage;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {

    // El constructor
    public MainPage() {
        super("Autenticacion");
        setLayout(null);
        setSize(650, 650); // Tamaño ventana
        setLocation(450, 200); // ubicación de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(""));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(188, 10, 100, 100);
        add(image);

        setVisible(true); // Hace visible la ventana

    }

    public static void Page(String[] args) {
        new MainPage();
    }
    
}

