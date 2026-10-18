package controlador;

import factory.FabricaEnvio;
import modelo.Envio;
import service.EnvioService;
import vista.VentanaEnvios;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.util.Map;

public class EnvioController {
    private final VentanaEnvios vista;
    private final EnvioService servicio;
    private final Map<String, FabricaEnvio> fabricas;

    public EnvioController(VentanaEnvios vista, EnvioService servicio,
                           Map<String, FabricaEnvio> fabricas) {
        this.vista = vista;
        this.servicio = servicio;
        this.fabricas = fabricas;
        conectarEventos();
        actualizarTabla();
    }

    private void conectarEventos() {
        vista.getBotonAgregar().addActionListener(e -> agregarEnvio());
        vista.getBotonRetirar().addActionListener(e -> retirarEnvio());
        vista.getBotonLimpiar().addActionListener(e -> vista.limpiarFormulario());
    }

    private void agregarEnvio() {
        try {
            String tipo = vista.getTipoSeleccionado();
            String codigo = textoObligatorio(vista.getCodigo(), "El código");
            String cliente = textoObligatorio(vista.getCliente(), "El nombre del cliente");
            BigDecimal peso = decimalPositivo(vista.getPeso(), "El peso");
            BigDecimal distancia = decimalPositivo(vista.getDistancia(), "La distancia");

            FabricaEnvio fabrica = fabricas.get(tipo);
            if (fabrica == null) {
                throw new IllegalArgumentException("Selecciona un tipo de envío válido.");
            }

            Envio envio = fabrica.crearEnvio(codigo, cliente, peso, distancia);
            servicio.agregar(envio);
            actualizarTabla();
            vista.limpiarFormulario();
            vista.mostrarMensaje("Envío agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje("El peso y la distancia deben ser números válidos.", "Dato inválido",
                    JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            vista.mostrarMensaje(ex.getMessage(), "Revisa los datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void retirarEnvio() {
        String codigo = vista.pedirCodigoRetiro();
        if (codigo == null) return;
        if (codigo.trim().isEmpty()) {
            vista.mostrarMensaje("Debes escribir el código del envío.", "Dato requerido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (servicio.retirar(codigo)) {
            actualizarTabla();
            vista.mostrarMensaje("Envío retirado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            vista.mostrarMensaje("No existe un envío con el código: " + codigo.trim(),
                    "No encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String textoObligatorio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " es obligatorio.");
        }
        return valor.trim();
    }

    private BigDecimal decimalPositivo(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " es obligatorio.");
        }
        // Acepta coma decimal o punto decimal.
        BigDecimal numero = new BigDecimal(valor.trim().replace(",", "."));
        if (numero.signum() <= 0) {
            throw new IllegalArgumentException(nombreCampo + " debe ser mayor que cero.");
        }
        return numero;
    }

    private void actualizarTabla() {
        vista.mostrarEnvios(servicio.listar());
    }
}
