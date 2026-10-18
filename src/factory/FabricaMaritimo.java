package factory;

import modelo.Envio;
import modelo.Maritimo;
import java.math.BigDecimal;

public class FabricaMaritimo extends FabricaEnvio {
    @Override
    public Envio crearEnvio(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        return new Maritimo(codigo, cliente, pesoKg, distanciaKm);
    }
}
