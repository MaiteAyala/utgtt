package com.ReservaHoteles.controller;

import com.ReservaHoteles.Repository.RolRepository;
import com.ReservaHoteles.model.Roles;
import com.ReservaHoteles.model.Usuario;
import com.ReservaHoteles.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return service.crear(usuario);
    }
    @GetMapping("/listar")
    public List<Usuario> listarUsuario(){
        return service.listarUsuario();
    }
    @DeleteMapping("eliminar/{id}")
    public void eliminarUsuario(@PathVariable("id") int idUsuario){
        service.eliminarUsuario(idUsuario);
    }

    @GetMapping("buscar/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable("id")  int idUsuario){
        return service.buscarUsuarioPorId(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @PutMapping("actualizar/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") int idUsuario, @RequestBody Usuario usuario){
    Usuario usuarioExistente = usuarioService.buscarUsuarioPorId(idUsuario).orElse(null);

    if(usuarioExistente != null) {

        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setRoles(usuario.getRoles());
        return usuarioService.crear(usuarioExistente);

    }


    return null;
}


    @GetMapping("/roles")
    public List<Roles> roles(){
        return rolRepository.findAll();
    }


}
