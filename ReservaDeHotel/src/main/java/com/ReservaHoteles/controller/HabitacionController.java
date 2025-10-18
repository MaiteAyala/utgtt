package com.ReservaHoteles.controller;

import com.ReservaHoteles.Repository.HabitacionRepository;

import com.ReservaHoteles.model.Habitacion;
import com.ReservaHoteles.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/habitacion")
public class HabitacionController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    HabitacionService habitacionService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearHabitacion(
            @RequestParam("numeroHabitacion") String numeroHabitacion,
            @RequestParam("tipo") String tipo,
            @RequestParam("precioPorNoche") double precioPorNoche,
            @RequestParam("estado") String estado,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen) {

        try {
            // Generar nombre único para la imagen
            String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            Path ruta = Paths.get(UPLOAD_DIR + nombreArchivo);
            Files.createDirectories(ruta.getParent());
            Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            // Crear y guardar la habitación
            Habitacion habitacion = new Habitacion();
            habitacion.setNumeroHabitacion(numeroHabitacion);
            habitacion.setTipo(tipo);
            habitacion.setPrecioPorNoche(precioPorNoche);
            habitacion.setEstado(estado);
            habitacion.setDescripcion(descripcion);
            habitacion.setImagen(nombreArchivo);

            habitacionRepository.save(habitacion);

            return ResponseEntity.ok(habitacion);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }

    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) {
        try {
            Path ruta = Paths.get(UPLOAD_DIR + nombre);
            Resource recurso = new UrlResource(ruta.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Ajusta según el tipo de imagen
                        .body(recurso);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Habitacion>> listarHabitaciones() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        return ResponseEntity.ok(habitaciones);
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarHabitacion(
            @PathVariable("id") int id,
            @RequestParam("numeroHabitacion") String numeroHabitacion,
            @RequestParam("tipo") String tipo,
            @RequestParam("precioPorNoche") double precioPorNoche,
            @RequestParam("estado") String estado,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        return habitacionRepository.findById(id).map(habitacion -> {
            habitacion.setNumeroHabitacion(numeroHabitacion);
            habitacion.setTipo(tipo);
            habitacion.setPrecioPorNoche(precioPorNoche);
            habitacion.setEstado(estado);
            habitacion.setDescripcion(descripcion);

            // Si se carga una nueva imagen, la reemplazamos
            if (imagen != null && !imagen.isEmpty()) {
                try {
                    String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                    Path ruta = Paths.get(UPLOAD_DIR + nombreArchivo);
                    Files.createDirectories(ruta.getParent());
                    Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
                    habitacion.setImagen(nombreArchivo);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error al subir la nueva imagen: " + e.getMessage());
                }
            }

            habitacionRepository.save(habitacion);
            return ResponseEntity.ok(habitacion);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}") // Se mapea a GET /habitacion/{id}
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable int id) {
        return habitacionRepository.findById(id)
                .map(ResponseEntity::ok) // Si se encuentra, devuelve 200 OK con la habitación
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404 Not Found
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarHabitacion(@PathVariable int id) {
      habitacionService.eliminarHabitacion(id);
    }

}
