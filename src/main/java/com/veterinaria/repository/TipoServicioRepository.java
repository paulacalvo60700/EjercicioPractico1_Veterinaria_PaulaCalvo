
package com.veterinaria.repository;

import com.veterinaria.domain.TipoServicio;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class TipoServicioRepository {

    private final Map<Integer, TipoServicio> datos = new LinkedHashMap<>();
    private int siguiente = 1;

    public TipoServicioRepository() {
        agregarInicial("Consulta General", "Revisión médica y diagnóstico");
        agregarInicial("Vacunación", "Esquemas de vacunas y refuerzos");
        agregarInicial("Estética", "Baño, corte y cuidado de pelaje");
        agregarInicial("Cirugía", "Procedimientos quirúrgicos programados");
    }

    private void agregarInicial(String nombre, String descripcion) {
        TipoServicio t = new TipoServicio();
        t.setId(siguiente++);
        t.setNombre(nombre);
        t.setDescripcion(descripcion);

        datos.put(t.getId(), t);
    }

    public synchronized List<TipoServicio> findAll() {
        return new ArrayList<>(datos.values());
    }

    public synchronized Optional<TipoServicio> findById(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    public synchronized void save(TipoServicio dato) {
        if (dato.getId() == null) {
            dato.setId(siguiente++);
        }
        datos.put(dato.getId(), dato);
    }

    public synchronized void deleteById(Integer id) {
        datos.remove(id);
    }
}
