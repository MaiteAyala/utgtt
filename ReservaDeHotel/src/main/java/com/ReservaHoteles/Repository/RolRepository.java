package com.ReservaHoteles.Repository;

import com.ReservaHoteles.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Roles, Integer> {
}
