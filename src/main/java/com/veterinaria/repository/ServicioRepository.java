
package com.veterinaria.repository;

import com.veterinaria.domain.Servicio;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.stereotype.Repository;

@Repository
public class ServicioRepository {

    private final Map<Integer, Servicio> datos = new LinkedHashMap<>();
    private int siguiente = 1;

    public ServicioRepository(TipoServicioRepository tipos) {
        agregarInicial("Consulta Medicina General", 18000, 30, 1, tipos);
        agregarInicial("Control de Peso y Nutrición", 15000, 30, 1, tipos);
        agregarInicial("Vacuna Antirrábica", 12500, 15, 2, tipos);
        agregarInicial("Vacuna Polivalente Canina", 16000, 15, 2, tipos);
        agregarInicial("Baño y Corte Raza Pequeña", 14000, 60, 3, tipos);
        agregarInicial("Esterilización Felina", 65000, 90, 4, tipos);
    }

    private void agregarInicial(String nombre, int precio, int minutos,
                               int tipo, TipoServicioRepository tipos) {

        Servicio s = new Servicio();
        s.setId(siguiente++);
        s.setNombre(nombre);
        s.setPrecio(BigDecimal.valueOf(precio));
        s.setDuracionMinutos(minutos);
        s.setTipoServicio(tipos.findById(tipo).orElseThrow());

        datos.put(s.getId(), s);
    }

    public synchronized List<Servicio> findAll() {
        return new ArrayList<>(datos.values());
    }

    public synchronized Optional<Servicio> findById(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    public synchronized void save(Servicio dato) {
        if (dato.getId() == null) {
            dato.setId(siguiente++);
        }
        datos.put(dato.getId(), dato);
    }

    public synchronized void deleteById(Integer id) {
        datos.remove(id);
    }

    public synchronized boolean existeTipo(Integer id) {
        return datos.values().stream()
                .anyMatch(s -> s.getTipoServicio().getId().equals(id));
    }
}
