package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import uniandes.edu.co.demo.modelo.Usuario;
import uniandes.edu.co.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuariosController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    } 

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(
    @RequestParam String nombre,
    @RequestParam String email,
    @RequestParam String nacionalidad,
    @RequestParam String telefono,
    @RequestParam int tipo_Usuario,
    @RequestParam int tipo_Empleado,
    @RequestParam String tipo_Doc,
    @RequestParam int num_Doc,
    @RequestParam String direccion,
    @RequestParam String ciudad,
    @RequestParam String departamento) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setNacionalidad(nacionalidad);
        usuario.setTelefono(telefono);
        usuario.setTipo_usuario(tipo_Usuario);
        usuario.setTipo_empleado(tipo_Empleado);
        usuario.setTipo_doc(tipo_Doc);
        usuario.setNum_doc(num_Doc);
        usuario.setDireccion(direccion);
        usuario.setCiudad(ciudad);
        usuario.setDepartamento(departamento);
        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }

}



    
