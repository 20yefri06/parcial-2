package factory;

import modelo.Envio;
import modelo.Aereo;
import java.math.BigDecimal;

public class FabricaAereo extends FabricaEnvio {
    @Override
    public Envio crearEnvio(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        return new Aereo(codigo, cliente, pesoKg, distanciaKm);
    }
}
