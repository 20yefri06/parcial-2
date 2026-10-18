package modelo;

import java.math.BigDecimal;

public class Aereo extends Envio {
    public Aereo(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        super(codigo, cliente, pesoKg, distanciaKm);
    }

    @Override public String getTipo() { return "Aéreo"; }
    @Override protected BigDecimal tarifaBasePorKm() { return new BigDecimal("5000"); }
    @Override protected BigDecimal recargoPorKg() { return new BigDecimal("4000"); }
}
