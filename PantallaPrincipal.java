
package Formulario;


import javax.swing.*;
//import javax.swing.Icon;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame implements ActionListener {

    private JTextField nameField, surnameField, resultField;
    private JComboBox<String> gradeBox, yearsBox;
    private JButton calculateButton, clearButton, backButton, changeBackgroundButton;
    private JLabel userLabel, footerLabel;
    private String nombreUsuario;
    private String apellido;
    private JPanel panel;
    private String[] grados = {"Elemento de Tropa", "Oficial Subalterno", "Oficial Superior"};
    private String[] aniosServicio = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private String[] fondoOpciones = {"Fondo 1", "Fondo 2", "Fondo 3"};  // Opciones de fondos
    private ImageIcon[] fondos;  // Array para almacenar las imágenes de fondo
    private ImageIcon fondoActual;  // Imagen del fondo actual

    public PantallaPrincipal(String nombre) {
        this.nombreUsuario = nombre;

        setTitle("Pantalla Principal");
        setSize(600, 400);
       // setIconImage(new ImageIcon(getClass().getResource("/icono.jfif")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Cargar las imágenes de fondo
        fondos = new ImageIcon[] {
            new ImageIcon("C:\\Users\\HP\\Pictures\\Ejercito.jpg"),  // Ruta de la primera imagen
            new ImageIcon("C:\\Users\\HP\\Pictures\\imagen2.jfif"),    // Ruta de la segunda imagen
            new ImageIcon("C:\\Users\\HP\\Pictures\\imagen3.jfif")     // Ruta de la tercera imagen
        };
        fondoActual = fondos[0];  // Establecer el fondo por defecto

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoActual.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon(""));
        logoLabel.setBounds(20, 10, 100, 50);
        panel.add(logoLabel);

        // Etiqueta de nombre de usuario que aceptó términos
        userLabel = new JLabel("Bienvenido: " + nombreUsuario);
        userLabel.setBounds(150, 10, 400, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel);

        // Campo para ingresar nombre
        JLabel nameLabel = new JLabel("Nombre Completo:");
        nameLabel.setBounds(50, 80, 140, 25);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(50, 100, 150, 25);
        panel.add(nameField);
        
        // Campo para ingresar apellido
        JLabel surnameLabel = new JLabel("Apellidos:");
        surnameLabel.setBounds(50, 130, 100, 25);
        surnameLabel.setForeground(Color.WHITE);
        panel.add(surnameLabel);

        surnameField = new JTextField();
        surnameField.setBounds(50, 150, 150, 25);
        panel.add(surnameField);

        // Combo box para seleccionar grado
        JLabel gradeLabel = new JLabel("Grado:");
        gradeLabel.setBounds(350, 80, 100, 25);
        gradeLabel.setForeground(Color.WHITE);
        panel.add(gradeLabel);

        gradeBox = new JComboBox<>(grados);
        gradeBox.setBounds(350, 100, 150, 25);
        panel.add(gradeBox);

        // Combo box para seleccionar años de servicio
        JLabel yearsLabel = new JLabel("Años de servicio:");
        yearsLabel.setBounds(350, 130, 100, 25);
        yearsLabel.setForeground(Color.WHITE);
        panel.add(yearsLabel);

        yearsBox = new JComboBox<>(aniosServicio);
        yearsBox.setBounds(350, 150, 150, 25);
        panel.add(yearsBox);

        // Campo para mostrar resultado del cálculo
        JLabel resultLabel = new JLabel("Resultado:");
        resultLabel.setBounds(50, 180, 100, 25);
        resultLabel.setForeground(Color.WHITE);
        panel.add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(50, 200, 200, 25);
        resultField.setEditable(false);
        panel.add(resultField);
        JScrollPane scrollpane1 = new JScrollPane(resultField);
        scrollpane1.setBounds(50,200,200,40); 
        add(scrollpane1);

        // Botón para calcular
        calculateButton = new JButton("Calcular");
        calculateButton.setBounds(50, 280, 100, 30);
        calculateButton.addActionListener(this);
        panel.add(calculateButton);

        // Botón para limpiar los campos
        clearButton = new JButton("Limpiar");
        clearButton.setBounds(160, 280, 100, 30);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        // Botón para regresar a la pantalla de bienvenida
        backButton = new JButton("Regresar");
        backButton.setBounds(270, 280, 100, 30);
        backButton.addActionListener(this);
        panel.add(backButton);

        // Botón para cambiar el fondo
        changeBackgroundButton = new JButton("Cambiar Fondo");
        changeBackgroundButton.setBounds(453, 0, 150, 30);
        changeBackgroundButton.addActionListener(this);
        panel.add(changeBackgroundButton);

        // Pie de página
        footerLabel = new JLabel("©Ejército de Guatemala | Yessica Chalí | Programación II | Sección B");
        footerLabel.setBounds(50, 330, 500, 30);
        footerLabel.setForeground(Color.BLACK);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(footerLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calcularVacaciones();
        } else if (e.getSource() == clearButton) {
            limpiarCampos();
        } else if (e.getSource() == backButton) {
            regresarBienvenida();
        } else if (e.getSource() == changeBackgroundButton) {
            cambiarFondo();
        }
    }

    private void calcularVacaciones() {
        // Recuperar nombre y apellido del usuario
        nombreUsuario = nameField.getText();
        apellido = surnameField.getText();
        
        // Capturar el grado y años de servicio seleccionados
        String grado = (String) gradeBox.getSelectedItem();
        int anios = Integer.parseInt((String) yearsBox.getSelectedItem());
        int diasVacaciones = 0;

        // Lógica para calcular días de vacaciones según el grado y los años de servicio
        if (grado.equals("Elemento de Tropa")) {
            if (anios == 1) {
                diasVacaciones = 6;
            } else if (anios >= 2 && anios <= 6) {
                diasVacaciones = 14;
            } else if (anios >= 7) {
                diasVacaciones = 20;
            }
        } else if (grado.equals("Oficial Subalterno")) {
            if (anios == 1) {
                diasVacaciones = 7;
            } else if (anios >= 2 && anios <= 6) {
                diasVacaciones = 15;
            } else if (anios >= 7) {
                diasVacaciones = 22;
            }
        } else if (grado.equals("Oficial Superior")) {
            if (anios == 1) {
                diasVacaciones = 10;
            } else if (anios >= 2 && anios <= 6) {
                diasVacaciones = 20;
            } else if (anios >= 7) {
                diasVacaciones = 30;
            }
        }

        // Mostrar resultado
        resultField.setText(" \n El Soldado  \n" + nombreUsuario + " " + apellido + "\n quien tiene el grado " + grado + 
                            " con " + anios + " \n años de antigüedad recibe " + diasVacaciones + " días de vacaciones.");
    }

    private void limpiarCampos() {
        nameField.setText("");
        surnameField.setText("");
        resultField.setText("");
        gradeBox.setSelectedIndex(0);
        yearsBox.setSelectedIndex(0);
    }

    private void regresarBienvenida() {
        dispose();
        new Bienvenida();
    }

    private void cambiarFondo() {
        // Mmostrar un diálogo para que el usuario elija el fondo
        String fondoSeleccionado = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione un fondo:",
            "Cambiar Fondo",
            JOptionPane.QUESTION_MESSAGE,
            null,
            fondoOpciones,
            fondoOpciones[0]
        );

        // Cambiar la imagen de fondo según la selección del usuario
        if (fondoSeleccionado != null) {
            switch (fondoSeleccionado) {
                case "Fondo 1":
                    fondoActual = fondos[0];
                    break;
                case "Fondo 2":
                    fondoActual = fondos[1];
                    break;
                case "Fondo 3":
                    fondoActual = fondos[2];
                    break;
            }
            panel.repaint();  // Repintar el panel para reflejar el cambio de fondo
        }
    }

    public static void main(String[] args) {
        new PantallaPrincipal("");
    }
}

