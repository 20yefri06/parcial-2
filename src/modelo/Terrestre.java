package modelo;

import java.math.BigDecimal;

public class Terrestre extends Envio {
    public Terrestre(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        super(codigo, cliente, pesoKg, distanciaKm);
    }

    @Override public String getTipo() { return "Terrestre"; }
    @Override protected BigDecimal tarifaBasePorKm() { return new BigDecimal("1500"); }
    @Override protected BigDecimal recargoPorKg() { return new BigDecimal("2000"); }
}
