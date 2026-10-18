package modelo;

import java.math.BigDecimal;

public abstract class Envio {
    private final String codigo;
    private final String cliente;
    private final BigDecimal pesoKg;
    private final BigDecimal distanciaKm;

    protected Envio(String codigo, String cliente, BigDecimal pesoKg, BigDecimal distanciaKm) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    public String getCodigo() { return codigo; }
    public String getCliente() { return cliente; }
    public BigDecimal getPesoKg() { return pesoKg; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }

    public abstract String getTipo();
    protected abstract BigDecimal tarifaBasePorKm();
    protected abstract BigDecimal recargoPorKg();

    // Polimorfismo: la implementación de cada subtipo determina su tarifa.
    public BigDecimal calcularTarifa() {
        return distanciaKm.multiply(tarifaBasePorKm())
                .add(pesoKg.multiply(recargoPorKg()));
    }
}
