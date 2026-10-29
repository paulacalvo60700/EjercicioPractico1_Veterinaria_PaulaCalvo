package com.veterinaria.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class InicioController {
    @GetMapping("/") public String inicio() { return "index"; }
    @GetMapping("/consulta") public String consulta() { return "consulta/formulario"; }
    @PostMapping("/consulta/enviar") public String enviar(@RequestParam String nombre,@RequestParam String correo,@RequestParam String mascota,@RequestParam String mensaje,RedirectAttributes redirect) {
        redirect.addFlashAttribute("todoOk","Gracias por tu consulta, "+nombre+". Pronto te contactaremos.");
        return "redirect:/consulta";
    }
}
