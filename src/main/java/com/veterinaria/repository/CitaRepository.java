package com.veterinaria.repository;
import com.veterinaria.domain.*;
import java.time.LocalDate;
import java.util.*;
import org.springframework.stereotype.Repository;
@Repository
public class CitaRepository {
    private final List<Cita> datos = new ArrayList<>();
    public CitaRepository(ServicioRepository servicios) {
        crear("Max","Carolina Jiménez","2026-11-04",1,servicios);
        crear("Luna","Diego Vargas","2026-11-06",3,servicios);
        crear("Toby","Mariana Rojas","2026-11-09",5,servicios);
        crear("Michi","Esteban Quesada","2026-11-12",6,servicios);
    }
    private void crear(String mascota,String propietario,String fecha,int idServicio,ServicioRepository servicios) {
        Cita c=new Cita(); c.setId(datos.size()+1); c.setNombreMascota(mascota); c.setNombrePropietario(propietario);
        c.setFecha(LocalDate.parse(fecha)); c.setServicio(servicios.findById(idServicio).orElseThrow()); datos.add(c);
    }
    public List<Cita> findAll() { return new ArrayList<>(datos); }
    public Optional<Cita> findById(Integer id) { return datos.stream().filter(c -> c.getId().equals(id)).findFirst(); }
    public boolean existeServicio(Integer id) { return datos.stream().anyMatch(c -> c.getServicio().getId().equals(id)); }
}
