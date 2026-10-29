package com.veterinaria.domain;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
@Data
public class TipoServicio {
    private Integer id;
    private String nombre;
    private String descripcion;
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<Servicio> servicios;
    public int getCantidadServicios() { return servicios == null ? 0 : servicios.size(); }
}
