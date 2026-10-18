package modelo;

import java.math.BigDecimal;

public class Maritimo extends Envio {
    public Maritimo(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        super(codigo, cliente, pesoKg, distanciaKm);
    }

    @Override public String getTipo() { return "Marítimo"; }
    @Override protected BigDecimal tarifaBasePorKm() { return new BigDecimal("800"); }
    @Override protected BigDecimal recargoPorKg() { return new BigDecimal("1000"); }
}
