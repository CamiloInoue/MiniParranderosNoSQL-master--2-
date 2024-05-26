package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.demo.modelo.Oficina;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.repository.OficinaRepository;
import uniandes.edu.co.demo.repository.PuntoAtencionRepository;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PuntosDeAtencionController {
    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping("/puntoDeAtencion")
    public String puntos(Model model) {
        model.addAttribute("puntosDeAtencion", puntoAtencionRepository.findAll());
        return "puntosDeAtencion";
    }

    @GetMapping("/puntosDeAtencion/new")
    public String puntoForm(Model model) {
        model.addAttribute("puntoDeAtencion", new PuntoAtencion());
        return "puntoNuevo";
    }

 @PostMapping("/puntosAtencion/new/save")
    public String puntoAtencionGuardar(@ModelAttribute PuntoAtencion puntoAtencion, @ModelAttribute("oficina") String nombreOficina) {
        PuntoAtencion puntoAt = puntoAtencionRepository.buscarPorId(puntoAtencion.getId());

        if(puntoAt != null){
            return "redirect:/error";
        }

        if(puntoAtencion.getTipo_punto() != "digital"){
            Oficina oficina = oficinaRepository.darOficinaPorNombre(nombreOficina);
            oficina.getPuntos_atencion().add(puntoAtencion.getId());
            oficinaRepository.save(oficina);
        }
        
        puntoAtencionRepository.save(puntoAtencion);
        return "redirect:/puntosDeAtencion";
    }
    
    @GetMapping("/puntos/{id}/delete")
    public String puntoEliminar(@PathVariable("id") Integer id) {
        puntoAtencionRepository.deleteById(id);
        List<Oficina> oficinas = oficinaRepository.findAll();

        for(Oficina o: oficinas){
            o.getPuntos_atencion().remove(id);
            oficinaRepository.save(o);
        }

        return "redirect:/puntosDeAtencion";
    }
}
