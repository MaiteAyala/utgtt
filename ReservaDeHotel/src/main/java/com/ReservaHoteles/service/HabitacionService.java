package com.ReservaHoteles.service;

import com.ReservaHoteles.model.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionService {
    Habitacion crear(Habitacion habitacion);
    List<Habitacion> listarHabitacion();
    Optional<Habitacion> buscarHabitacionPorId(int idHabitacion);
    void eliminarHabitacion(int idHabitacion);
}
