package com.veterinaria.domain;
import java.time.LocalDate;
import lombok.Data;
@Data
public class Cita {
    private Integer id;
    private String nombreMascota;
    private String nombrePropietario;
    private LocalDate fecha;
    private Servicio servicio;
}
