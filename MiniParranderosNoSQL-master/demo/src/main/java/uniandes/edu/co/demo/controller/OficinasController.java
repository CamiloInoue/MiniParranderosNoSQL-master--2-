package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.repository.OficinaRepository;

@Controller
public class OficinasController {
    
    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/oficinas")
    public String oficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.findAll());
        return "oficinas";
    }
    

    @GetMapping("/oficinas/new")
    public String oficinaForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        return "oficinasNuevo";
    }

    @PostMapping("/oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
        List<Integer> puntosAtRelacionados = new ArrayList<>();
        oficina.setPuntos_atencion(puntosAtRelacionados);
        oficinaRepository.save(oficina);
        return "redirect:/oficinas";

    }

}
