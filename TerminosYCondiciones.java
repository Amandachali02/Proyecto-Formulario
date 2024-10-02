
package Formulario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminosYCondiciones extends JFrame implements ActionListener {

    private JCheckBox acceptCheckBox;
    private JButton continueButton, noAcceptButton;
    private String nombre;

    public TerminosYCondiciones(String nombre) {
        this.nombre = nombre;

        setTitle("Licencia de uso");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("TÉRMINOS Y CONDICIONES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(200, 10, 300, 30);
        panel.add(titleLabel);

        JTextArea termsArea = new JTextArea("\n          TÉRMINOS Y CONDICIONES\n" +
                "\n" +
                "            A.  PROHIBIDA SU VENTA O DISTRIBUCIÓN SIN AUTORIZACIÓN DE LA GEEKIPEDIA DE YESSICA.\n" +
                "            B.  PROHIBIDA LA ALTERACIÓN DEL CÓDIGO FUENTE O DISEÑO DE LAS INTERFACES GRÁFICAS.\n" +
                "            C.  LA GEEKIPEDIA DE YESSICA NO SE HACE RESPONSABLE DEL MAL USO DE ESTE SOFTWARE.\n" +
                "\n" +
                "          LOS ACUERDOS LEGALES EXPUESTOS ACONTINUACIÓN RIGEN EL USO QUE USTED HAGA DE ESTE SOFTWARE\n" +
                "          (LA GEEKIPEDIA DE YESSICA Y EL AUTOR YESSICA), NO SE RESPONSABILIZAN DEL USO QUE USTED\n" +
                "          HAGA CON ESTE SOFTWARE Y SUS SERVICIOS. PARA ACEPTAR ESTOS TERMINOS HAGA CLIC EN (ACEPTO)\n" +
                "          SI USTED NO ACEPTA ESTOS TERMINOS, HAGA CLIC EN (NO ACEPTO) Y NO UTILICE ESTE SOFTWARE.\n" +
                "\n" +
                "          PARA MAYOR INFORMACIÓN SOBRE NUESTROS PRODUCTOS O SERVICIOS, POR FAVOR VISITE\n" +
                "          http://www.youtube.com/YessicaChalí");
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        termsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(termsArea);
        scrollPane.setBounds(30, 50, 640, 200);
        panel.add(scrollPane);

        acceptCheckBox = new JCheckBox("Yo " + nombre + " Acepto");
        acceptCheckBox.setBounds(30, 260, 300, 30);
        panel.add(acceptCheckBox);

        continueButton = new JButton("Continuar");
        continueButton.setBounds(100, 300, 120, 30);
        continueButton.addActionListener(this);
        continueButton.setEnabled(false);
        panel.add(continueButton);

        noAcceptButton = new JButton("No Acepto");
        noAcceptButton.setBounds(250, 300, 120, 30);
        noAcceptButton.addActionListener(this);
        panel.add(noAcceptButton);

        acceptCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueButton.setEnabled(acceptCheckBox.isSelected());
            }
        });

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            dispose(); // Cerrar ventana actual
            
          new PantallaPrincipal(nombre); // Abrir la pantalla principal
        } else if (e.getSource() == noAcceptButton) {
            JOptionPane.showMessageDialog(this, "No has aceptado los términos.", "No Aceptado", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TerminosYCondiciones("");
    }
}