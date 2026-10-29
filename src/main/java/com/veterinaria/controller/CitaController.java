package com.veterinaria.controller;
import com.veterinaria.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller @RequestMapping("/cita")
public class CitaController {
    private final CitaService servicio;
    public CitaController(CitaService servicio) { this.servicio=servicio; }
    @GetMapping("/listado") public String listado(Model model) { model.addAttribute("citas",servicio.listar()); return "cita/listado"; }
}
