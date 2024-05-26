package uniandes.edu.co.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.modelo.OperacionCuenta;
import uniandes.edu.co.demo.modelo.PuntoAtencion;
import uniandes.edu.co.demo.modelo.Cuenta;
import uniandes.edu.co.demo.repository.CuentaRepository;
import uniandes.edu.co.demo.repository.PuntoAtencionRepository;
import uniandes.edu.co.demo.repository.CuentaRepository;

import org.springframework.transaction.annotation.Transactional;


@Controller
public class OperacionCuentasController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PuntoAtencionRepository puntoAtencionRepository;

    
    @GetMapping("/consignar/new")
    public String consignarForm(Model model) {
        model.addAttribute("operacion_cuenta", new OperacionCuenta());
        model.addAttribute("puntosAtencion", puntoAtencionRepository.findAll());
        return "consignar";
    }

    @GetMapping("/retirar/new")
    public String retirarForm(Model model) {
        model.addAttribute("operacion_cuenta", new OperacionCuenta());
        model.addAttribute("puntosAtencion", puntoAtencionRepository.puntosAtCajeroYPersonalizado());
        return "retirar";
    }

    @PostMapping("/retirar/new/save")
    @Transactional
    public String retirarDinero(@RequestParam("numero_cuenta") int numero_cuenta, Model model, @ModelAttribute OperacionCuenta operacion_cuenta,@ModelAttribute("puntoAtencion") int idPuntoAtencion) {

        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numero_cuenta);
        Cuenta cuentaModificado;
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setSaldo(cuenta.getSaldo() - operacion_cuenta.getMonto_pago());
                    List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                    operacionesCuenta.add(new OperacionCuenta(operacion_cuenta.getTipo(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getMonto_pago(), 0));
                    cuentaModificado = cuenta;
                    cuentaRepository.delete(cuenta);
                    cuentaRepository.save(cuentaModificado);
                    return "redirect:/cuentas";

        }
        return "redirect:/error";
        
    }
    
    @PostMapping("/consignar/new/save")
    public String consignarDinero(@RequestParam("numero_cuenta") int numero_cuenta,  Model model, @ModelAttribute OperacionCuenta operacion_cuenta,@ModelAttribute("puntoAtencion") int idPuntoAtencion) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numero_cuenta);
        Cuenta cuentaModificado;
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            cuenta.setSaldo(cuenta.getSaldo() + operacion_cuenta.getMonto_pago());
                    List<OperacionCuenta> operacionesCuenta = cuenta.getOperaciones_cuenta();
                    operacionesCuenta.add(new OperacionCuenta(operacion_cuenta.getTipo(), operacion_cuenta.getFecha_operacion(), operacion_cuenta.getMonto_pago(), 0));
                    System.out.print(cuenta);
                    cuentaModificado = cuenta;
                    cuentaRepository.delete(cuenta);
                    cuentaRepository.save(cuentaModificado);

                    return "redirect:/cuentas";

        }
        return "redirect:/error";
    }

}
