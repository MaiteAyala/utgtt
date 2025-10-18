package com.ReservaHoteles.service;

import com.ReservaHoteles.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {
    Usuario crear(Usuario usuario);
    List<Usuario> listarUsuario();
    Optional<Usuario> buscarUsuarioPorId(int idUsuario);
    void eliminarUsuario(int idUsuario);
}
