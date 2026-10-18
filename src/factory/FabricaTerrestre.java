package factory;

import modelo.Envio;
import modelo.Terrestre;
import java.math.BigDecimal;

public class FabricaTerrestre extends FabricaEnvio {
    @Override
    public Envio crearEnvio(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        return new Terrestre(codigo, cliente, pesoKg, distanciaKm);
    }
}
