package vista;

import modelo.Envio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class VentanaEnvios extends JFrame {
    private final JComboBox<String> comboTipo;
    private final JTextField campoCodigo = new JTextField(12);
    private final JTextField campoCliente = new JTextField(18);
    private final JTextField campoPeso = new JTextField(8);
    private final JTextField campoDistancia = new JTextField(8);
    private final JButton botonAgregar = new JButton("Agregar envío");
    private final JButton botonRetirar = new JButton("Retirar por código");
    private final JButton botonLimpiar = new JButton("Limpiar");
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final NumberFormat moneda = NumberFormat.getCurrencyInstance(Locale.of("es", "CO"));

    
    public VentanaEnvios(String[] tiposEnvio) {
        super("Sistema de gestión de envíos");
        comboTipo = new JComboBox<>(tiposEnvio);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 540);
        setMinimumSize(new Dimension(760, 460));
        setLocationRelativeTo(null);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del envío"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 8, 6, 8);
        c.anchor = GridBagConstraints.WEST;

        agregarCampo(formulario, c, 0, 0, "Tipo:", comboTipo);
        agregarCampo(formulario, c, 1, 0, "Código único:", campoCodigo);
        agregarCampo(formulario, c, 0, 1, "Cliente/remitente:", campoCliente);
        agregarCampo(formulario, c, 1, 1, "Peso (kg):", campoPeso);
        agregarCampo(formulario, c, 0, 2, "Distancia (km):", campoDistancia);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        acciones.add(botonAgregar);
        acciones.add(botonRetirar);
        acciones.add(botonLimpiar);

        modeloTabla = new DefaultTableModel(
                new Object[]{"Código", "Cliente", "Tipo", "Peso (kg)", "Distancia (km)", "Tarifa"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Envíos registrados"));

        JLabel nota = new JLabel("Tarifa = (distancia × tarifa base por km) + (peso × recargo por kg)");
        nota.setBorder(BorderFactory.createEmptyBorder(4, 8, 8, 8));

        setLayout(new BorderLayout(8, 8));
        add(formulario, BorderLayout.NORTH);
        JPanel centro = new JPanel(new BorderLayout());
        centro.add(acciones, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(nota, BorderLayout.SOUTH);
        add(centro, BorderLayout.CENTER);
    }

    private void agregarCampo(JPanel panel, GridBagConstraints c, int columna, int fila,
                              String etiqueta, Component componente) {
        c.gridx = columna * 2;
        c.gridy = fila;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(etiqueta), c);

        c.gridx = columna * 2 + 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(componente, c);
    }

    public void mostrarEnvios(List<Envio> envios) {
        modeloTabla.setRowCount(0);
        for (Envio envio : envios) {
            // La llamada a calcularTarifa() es polimórfica: no se pregunta el subtipo.
            modeloTabla.addRow(new Object[]{
                    envio.getCodigo(),
                    envio.getCliente(),
                    envio.getTipo(),
                    envio.getPesoKg().stripTrailingZeros().toPlainString(),
                    envio.getDistanciaKm().stripTrailingZeros().toPlainString(),
                    moneda.format(envio.calcularTarifa())
            });
        }
    }

    public void limpiarFormulario() {
        campoCodigo.setText("");
        campoCliente.setText("");
        campoPeso.setText("");
        campoDistancia.setText("");
        campoCodigo.requestFocusInWindow();
    }

    public String getTipoSeleccionado() { return (String) comboTipo.getSelectedItem(); }
    public String getCodigo() { return campoCodigo.getText(); }
    public String getCliente() { return campoCliente.getText(); }
    public String getPeso() { return campoPeso.getText(); }
    public String getDistancia() { return campoDistancia.getText(); }
    public JButton getBotonAgregar() { return botonAgregar; }
    public JButton getBotonRetirar() { return botonRetirar; }
    public JButton getBotonLimpiar() { return botonLimpiar; }

    public String pedirCodigoRetiro() {
        return JOptionPane.showInputDialog(this, "Escribe el código del envío que deseas retirar:",
                "Retirar envío", JOptionPane.QUESTION_MESSAGE);
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoMensaje);
    }
}
