package com.ReservaHoteles.controller;

import com.ReservaHoteles.Repository.ClienteRepository;
import com.ReservaHoteles.model.Cliente;

import com.ReservaHoteles.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService service;
    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/crear")
    public Cliente crear(@RequestBody Cliente cliente){
        return service.crear(cliente);
    }

    @GetMapping("/listar")
    public List<Cliente> listar(){
        return service.listarCliente();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        try {
            // Verificar si el cliente existe
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if (clienteOptional.isEmpty()) {
                response.put("error", "Cliente no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            service.eliminarCliente(id);
            response.put("mensaje", "Cliente eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            response.put("error", "No se puede eliminar el cliente debido a relaciones existentes");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            response.put("error", "Error inesperado al eliminar el cliente");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/buscar/{id}")
    public Cliente buscarPorId(@PathVariable("id")  int idCliente){
        return service.buscarClientePorId(idCliente).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Map<String, String>> actualizarCliente(@PathVariable("id") int idCliente, @RequestBody Cliente cliente) {
        Map<String, String> response = new HashMap<>();

        // Buscar el cliente por ID
        Cliente clienteExistente = clienteRepository.findById(idCliente).orElse(null);

        if (clienteExistente != null) {
            // Actualizar los campos del cliente existente
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDni(cliente.getDni());
            clienteExistente.setTelefono(cliente.getTelefono());
            clienteExistente.setNacionalidad(cliente.getNacionalidad());
            // Otros campos que necesites actualizar

            // Guardar el cliente actualizado
            clienteRepository.save(clienteExistente); // Actualiza el cliente en la base de datos

            // Responder con éxito
            response.put("mensaje", "Cliente actualizado correctamente");
            return ResponseEntity.ok(response); // Retorna el mensaje de éxito
        }

        // Si el cliente no existe
        response.put("error", "Cliente no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Responde con error si no se encuentra el cliente
    }

}
