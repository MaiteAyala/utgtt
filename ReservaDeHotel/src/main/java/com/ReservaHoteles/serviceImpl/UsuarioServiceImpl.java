package com.ReservaHoteles.serviceImpl;

import com.ReservaHoteles.Repository.UsuarioRepository;
import com.ReservaHoteles.model.Usuario;
import com.ReservaHoteles.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario crear(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return repo.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return repo.findAll();
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(int idUsuario) {
        return repo.findById(idUsuario);
    }

    @Override
    public void eliminarUsuario(int idUsuario) {
        repo.deleteById(idUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repo.findByEmail(email);

        if (usuario == null){
            throw  new  UsernameNotFoundException("email no encontrado") ;
        }



        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombreRol())).
                        collect(Collectors.toList())
        );
    }
}
