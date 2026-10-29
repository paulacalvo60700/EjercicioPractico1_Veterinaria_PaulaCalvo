package com.veterinaria.controller;
import com.veterinaria.domain.Servicio;
import com.veterinaria.service.ServicioService;
import com.veterinaria.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/servicio")
public class ServicioController {
    private final ServicioService servicio;
    private final TipoServicioService tiposService;
    public ServicioController(ServicioService servicio, TipoServicioService tiposService) { this.servicio=servicio; this.tiposService=tiposService; }
    @GetMapping("/listado") public String listado(Model model) { model.addAttribute("servicios",servicio.listar()); model.addAttribute("tipos",tiposService.listar()); model.addAttribute("dato",new Servicio()); return "servicio/listado"; }
    @GetMapping("/modificar/{id}") public String modificar(@PathVariable Integer id,Model model) {
        var dato=servicio.buscar(id);
        if(dato.isEmpty()) return "redirect:/servicio/listado";
        model.addAttribute("dato",dato.get()); model.addAttribute("tipos",tiposService.listar()); return "servicio/modifica";
    }
    @PostMapping("/guardar") public String guardar(@ModelAttribute Servicio dato,RedirectAttributes redirect) {
        try { servicio.guardar(dato); redirect.addFlashAttribute("todoOk","Registro guardado correctamente"); }
        catch(Exception e) { redirect.addFlashAttribute("error","No se pudo guardar el registro"); }
        return "redirect:/servicio/listado";
    }
    @PostMapping("/eliminar") public String eliminar(@RequestParam Integer id,RedirectAttributes redirect) {
        try { servicio.eliminar(id); redirect.addFlashAttribute("todoOk","Registro eliminado correctamente"); }
        catch(Exception e) { redirect.addFlashAttribute("error","No se puede eliminar: tiene registros asociados o ocurrió un error"); }
        return "redirect:/servicio/listado";
    }
}
