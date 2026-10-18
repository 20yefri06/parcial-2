import controlador.EnvioController;
import factory.FabricaAereo;
import factory.FabricaEnvio;
import factory.FabricaMaritimo;
import factory.FabricaTerrestre;
import service.EnvioService;
import vista.VentanaEnvios;

import javax.swing.SwingUtilities;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnvioService servicio = new EnvioService();

            // Mapa de fábricas: el controlador trabaja con la abstracción FabricaEnvio.
            Map<String, FabricaEnvio> fabricas = new LinkedHashMap<>();
            fabricas.put("Terrestre", new FabricaTerrestre());
            fabricas.put("Aéreo", new FabricaAereo());
            fabricas.put("Marítimo", new FabricaMaritimo());

            VentanaEnvios vista = new VentanaEnvios(fabricas.keySet().toArray(new String[0]));
            new EnvioController(vista, servicio, fabricas);
            vista.setVisible(true);
        });
    }
}
