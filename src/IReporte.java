import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class IReporte extends JFrame {

    public IReporte(Map<String, Integer> equiposPorProfesor, Map<String, Double> montoPorProfesor) {
        setTitle("Generar reporte");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(10, 30);
        add(new JScrollPane(textArea));

        JButton btnGenerarReporte = new JButton("Generar reporte");
        add(btnGenerarReporte);

        btnGenerarReporte.addActionListener(e -> {
            StringBuilder reporte = new StringBuilder();

            for (String profesor : equiposPorProfesor.keySet()) {
                reporte.append("Profesor: ").append(profesor).append("\n");
                reporte.append("Número total de equipos: ").append(equiposPorProfesor.get(profesor)).append("\n");
                reporte.append("Monto total en bolívares: ").append(montoPorProfesor.get(profesor)).append("\n");
            }

            textArea.setText(reporte.toString());
        });
    }

    public static void main(String[] args) {
        // Debes pasar las instancias de equiposPorProfesor y montoPorProfesor al constructor
        SwingUtilities.invokeLater(() -> {
            new IReporte(new HashMap<>(), new HashMap<>()).setVisible(true);
        });
    }
}