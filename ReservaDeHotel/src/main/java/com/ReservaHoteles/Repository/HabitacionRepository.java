package com.ReservaHoteles.Repository;

import com.ReservaHoteles.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {
}
