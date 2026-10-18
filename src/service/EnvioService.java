package service;

import modelo.Envio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EnvioService {
    private final List<Envio> envios = new ArrayList<>();

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo).isPresent();
    }

    public void agregar(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo.");
        }
        if (existeCodigo(envio.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un envío con ese código.");
        }
        envios.add(envio);
    }

    public boolean retirar(String codigo) {
        return envios.removeIf(envio -> envio.getCodigo().equalsIgnoreCase(codigo.trim()));
    }

    public Optional<Envio> buscarPorCodigo(String codigo) {
        if (codigo == null) return Optional.empty();
        String buscado = codigo.trim();
        return envios.stream()
                .filter(envio -> envio.getCodigo().equalsIgnoreCase(buscado))
                .findFirst();
    }

    public List<Envio> listar() {
        return Collections.unmodifiableList(new ArrayList<>(envios));
    }
}
