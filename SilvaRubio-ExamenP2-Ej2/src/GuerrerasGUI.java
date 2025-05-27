import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuerrerasGUI {
    private JPanel pGeneral;
    private JTextField textFieldID;
    private JTextField textFieldAlias;
    private JComboBox comboBoxPoder;
    private JComboBox comboBoxnivelE;
    private JComboBox comboBoxUbi;
    private JButton agregarGuerreraButton;
    private JTable table1;
    private JTextField textFieldbuscar;
    private JButton buscarButton;
    private JTextArea textArea1;
    private JComboBox comboBoxFiltrado;
    private JButton filtrarYOrdenarButton;
    private JTable table2;
    private JComboBox comboBoxubi2;
    private JButton contarGuerrerasButton;
    private JTextArea textArea2;
    private JButton limpiarCamposButton;

    private ListaGuerrera lista = new ListaGuerrera();

    public GuerrerasGUI() {



        agregarGuerreraButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldID.getText());
                String alias = textFieldAlias.getText();
                String poder = comboBoxPoder.getSelectedItem().toString();
                int nivel = Integer.parseInt(comboBoxnivelE.getSelectedItem().toString());
                String ubicacion = comboBoxUbi.getSelectedItem().toString();

                GuerreraBrightMoon v = new GuerreraBrightMoon(id, alias, poder, nivel, ubicacion);
                if (lista.insertarOrdenado(v)) {
                    JOptionPane.showMessageDialog(null, "Guerrera agregada correctamente");
                    actualizarTabla(table1, lista.toArray());

                } else {
                    JOptionPane.showMessageDialog(null, "ID ya existente");
                }
                if (alias.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Alias no puede estar vacío");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID debe ser numérico");
            }
        });


        contarGuerrerasButton.addActionListener(e -> {
            String ubicacion = comboBoxubi2.getSelectedItem().toString();
            int cantidad = lista.contarPorUbicacion(ubicacion);
            textArea2.setText("Cantidad de guerreras en " + ubicacion + ": " + cantidad);
        });


        filtrarYOrdenarButton.addActionListener(e -> {
            String especialidad = comboBoxFiltrado.getSelectedItem().toString();
            java.util.List<GuerreraBrightMoon> filtradas = lista.filtrarPorPoderYOrdenar(especialidad);
            actualizarTabla(table2, filtradas);
        });


        buscarButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldbuscar.getText());
                GuerreraBrightMoon v = lista.buscarPorID(id);
                if (v != null) {
                    textArea1.setText("ID: " + v.getID() +
                            "\nAlias: " + v.getAlias() +
                            "\nEspecialidad: " + v.getPoderBatalla() +
                            "\nNivel: " + v.getNivelEstrategia() +
                            "\nUbicación: " + v.getUbicacion());
                } else {
                    textArea1.setText("No se encontró la guerrera");
                }
            } catch (NumberFormatException ex) {
                textArea1.setText("ID inválido");
            }
        });

        limpiarCamposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldID.setText("");
                textFieldAlias.setText("");
                textFieldbuscar.setText("");
                textArea1.setText("");
                textArea2.setText("");
               comboBoxPoder.setSelectedIndex(0);
               comboBoxnivelE.setSelectedIndex(0);
               comboBoxUbi.setSelectedIndex(0);
               comboBoxFiltrado.setSelectedIndex(0);
               comboBoxubi2.setSelectedIndex(0);
            }
        });
    }

    private void actualizarTabla(JTable tabla, java.util.List<GuerreraBrightMoon> guerreras) {
        String[] columnas = {"ID", "Alias", "Especialidad", "Nivel", "Ubicación"};
        String[][] datos = new String[guerreras.size()][5];

        for (int i = 0; i < guerreras.size(); i++) {
            GuerreraBrightMoon v = guerreras.get(i);
            datos[i][0] = String.valueOf(v.getID());
            datos[i][1] = v.getAlias();
            datos[i][2] = v.getPoderBatalla();
            datos[i][3] = String.valueOf(v.getNivelEstrategia());
            datos[i][4] = v.getUbicacion();
        }

        tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GuerrerasGUI");
        frame.setContentPane(new GuerrerasGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

