import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ICentro extends JFrame {

    private Map<String, Integer> equiposPorProfesor = new HashMap<>();
    private Map<String, Double> montoPorProfesor = new HashMap<>();

    public ICentro() {
        setTitle("Registro de Equipos por Profesor");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(10, 30);
        add(new JScrollPane(textArea));

        JButton btnGuardar = new JButton("Guardar datos");
        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            String inputData = textArea.getText();
            String[] lines = inputData.split("\n");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventario.txt", true))) {
                for (String input : lines) {
                    String[] datos = input.split("#");
                    String profesor = datos[6];

                    int cantidadEquipos = Integer.parseInt(datos[1]);
                    double costoUnitario = Double.parseDouble(datos[2]);
                    double costoTotal = cantidadEquipos * costoUnitario;

                    // Guardar en inventario.txt
                    writer.write(input);
                    writer.newLine();

                    // Actualizar datos por profesor
                    equiposPorProfesor.put(profesor, equiposPorProfesor.getOrDefault(profesor, 0) + cantidadEquipos);
                    montoPorProfesor.put(profesor, montoPorProfesor.getOrDefault(profesor, 0.0) + costoTotal);
                }
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
            } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.err.println("Error al procesar los datos: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ICentro().setVisible(true);
        });
    }
}