package com.ReservaHoteles.serviceImpl;

import com.ReservaHoteles.Repository.HabitacionRepository;
import com.ReservaHoteles.model.Habitacion;
import com.ReservaHoteles.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements HabitacionService {
    @Autowired
    HabitacionRepository repo;

    @Override
    public Habitacion crear(Habitacion habitacion) {
        return repo.save(habitacion);
    }

    @Override
    public List<Habitacion> listarHabitacion() {
        return repo.findAll();
    }

    @Override
    public Optional<Habitacion> buscarHabitacionPorId(int idHabitacion) {
        return repo.findById(idHabitacion);
    }

    @Override
    public void eliminarHabitacion(int idHabitacion) {
    repo.deleteById(idHabitacion);
    }
}
