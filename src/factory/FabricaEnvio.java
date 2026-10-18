package factory;

import modelo.Envio;
import java.math.BigDecimal;

/**
 * Creador abstracto del patrón Factory Method.
 * Cada fábrica concreta decide qué subtipo de Envio crear.
 */
public abstract class FabricaEnvio {
    public abstract Envio crearEnvio(
            String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm);
}
