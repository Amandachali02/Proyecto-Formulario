
package Formulario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bienvenida extends JFrame implements ActionListener {

    private JTextField nameField;
    private JButton ingresarButton;
    private String nombre;

    public Bienvenida() {
        setTitle("Bienvenido");
        
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("C:\\Users\\HP\\Pictures\\Ejercito.jpg");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Sistema de control Vacacional");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(50, 30, 300, 30);
        panel.add(titleLabel);

        JLabel nameLabel = new JLabel("Ingrese su nombre:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setBounds(50, 100, 150, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(50, 130, 300, 30);
        panel.add(nameField);

        ingresarButton = new JButton("Ingresar");
        ingresarButton.setBounds(150, 180, 100, 30);
        ingresarButton.addActionListener(this);
        panel.add(ingresarButton);

        JLabel footerLabel = new JLabel("©EJERCITO DE GUATEMALA ");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setBounds(100, 300, 200, 25);
        panel.add(footerLabel);

        JLabel footerLabel2 = new JLabel("Yessica Chalí, Programación II, sección B");
        footerLabel2.setForeground(Color.BLACK);
        footerLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        footerLabel2.setBounds(50, 330, 300, 25);
        panel.add(footerLabel2);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresarButton) {
            nombre = nameField.getText();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese su nombre", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
                new TerminosYCondiciones(nombre); // esto abre la segunda clase que es terminos y condiciones
            }
        }
    }

    public static void main(String[] args) {
        new Bienvenida();
    }
}

