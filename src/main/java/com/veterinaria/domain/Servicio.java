package com.veterinaria.domain;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class Servicio {
    private Integer id;
    private String nombre;
    private BigDecimal precio;
    private Integer duracionMinutos;
    private TipoServicio tipoServicio;
}
