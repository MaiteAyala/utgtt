package com.ReservaHoteles.service;

import com.ReservaHoteles.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarCliente();
    Cliente crear(Cliente cliente);
    Optional<Cliente> buscarClientePorId(int idCliente);
    void eliminarCliente(int idCliente);
}
