package com.ReservaHoteles.controller;

import com.ReservaHoteles.Repository.UsuarioRepository;
import com.ReservaHoteles.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioRequest) {
        Usuario usuarioBD = usuarioRepository.findByEmail(usuarioRequest.getEmail());

        if (usuarioBD == null) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }

        // Verifica la contraseña encriptada
        if (!passwordEncoder.matches(usuarioRequest.getPassword(), usuarioBD.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        // Retorna los datos del usuario (puedes personalizar la respuesta)
        Map<String, Object> response = new HashMap<>();
        response.put("id", usuarioBD.getIdUsuario());
        response.put("email", usuarioBD.getEmail());
        response.put("roles", usuarioBD.getRoles());

        return ResponseEntity.ok(response);
    }
}
