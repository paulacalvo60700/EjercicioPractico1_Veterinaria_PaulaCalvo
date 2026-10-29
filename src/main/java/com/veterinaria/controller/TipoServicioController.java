package com.veterinaria.controller;
import com.veterinaria.domain.TipoServicio;
import com.veterinaria.service.TipoServicioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/tipoServicio")
public class TipoServicioController {
    private final TipoServicioService servicio;
    
    public TipoServicioController(TipoServicioService servicio) { this.servicio=servicio;  }
    @GetMapping("/listado") public String listado(Model model) { model.addAttribute("tipos",servicio.listar());  model.addAttribute("dato",new TipoServicio()); return "tipoServicio/listado"; }
    @GetMapping("/modificar/{id}") public String modificar(@PathVariable Integer id,Model model) {
        var dato=servicio.buscar(id);
        if(dato.isEmpty()) return "redirect:/tipoServicio/listado";
        model.addAttribute("dato",dato.get());  return "tipoServicio/modifica";
    }
    @PostMapping("/guardar") public String guardar(@ModelAttribute TipoServicio dato,RedirectAttributes redirect) {
        try { servicio.guardar(dato); redirect.addFlashAttribute("todoOk","Registro guardado correctamente"); }
        catch(Exception e) { redirect.addFlashAttribute("error","No se pudo guardar el registro"); }
        return "redirect:/tipoServicio/listado";
    }
    @PostMapping("/eliminar") public String eliminar(@RequestParam Integer id,RedirectAttributes redirect) {
        try { servicio.eliminar(id); redirect.addFlashAttribute("todoOk","Registro eliminado correctamente"); }
        catch(Exception e) { redirect.addFlashAttribute("error","No se puede eliminar: tiene registros asociados o ocurrió un error"); }
        return "redirect:/tipoServicio/listado";
    }
}
