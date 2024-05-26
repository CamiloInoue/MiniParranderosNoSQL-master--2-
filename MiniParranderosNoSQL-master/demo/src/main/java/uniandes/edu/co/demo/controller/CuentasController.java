package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.repository.CuentaRepository;

@Controller
public class CuentasController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping("/cuentas")
    public String darCuentas(Model model) {
        model.addAttribute("cuentas", cuentaRepository.findAll());
        return "cuentas";
    }

    @GetMapping("/cuentas/new")
    public String nuevaCuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNueva";
    }

     @PostMapping("cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, int num_doc_cliente, Model model) { 
        List<OperacionCuenta> operacionesCuenta = new ArrayList<OperacionCuenta>();
        cuenta.setOperaciones_cuenta(operacionesCuenta);
        long millis=System.currentTimeMillis();
        Date hoy = new Date(millis);
        cuenta.setFechaCreacion(hoy);
        cuenta.setNum_doc_cliente(num_doc_cliente);
        cuentaRepository.save(cuenta);
        return "redirect:/cuentas";
    }


    @GetMapping("/cuentas/{id}/edit")
    public String editarCuentaForm(@PathVariable Integer id, Model model) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (cuenta != null) {
            model.addAttribute("cuenta", cuenta);
            return "cuentaEditar";
        } else {
            return "redirect:/cuentas";
        }
    }

    @PostMapping("/cuentas/{id}/edit/save")
    public String guardarCuentaEditada(@RequestParam("id") Integer id, @ModelAttribute Cuenta cuenta) {
        cuenta.setNumero_cuenta(id);
        cuentaRepository.save(cuenta);
        return "redirect:/cuentas";
    }

    @GetMapping("/cuentas/{id}/delete")
    public String eliminarCuenta(@PathVariable Integer id) {
        cuentaRepository.deleteById(id);
        return "redirect:/cuentas";
    }
}
