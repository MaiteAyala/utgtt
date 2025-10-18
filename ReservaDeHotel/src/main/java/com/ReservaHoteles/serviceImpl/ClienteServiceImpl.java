package com.ReservaHoteles.serviceImpl;

import com.ReservaHoteles.Repository.ClienteRepository;
import com.ReservaHoteles.model.Cliente;
import com.ReservaHoteles.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository repo;


    @Override
    public List<Cliente> listarCliente() {
        return repo.findAll();
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarClientePorId(int idCliente) {
        return repo.findById(idCliente);
    }

    @Override
    public void eliminarCliente(int idCliente) {
    repo.deleteById(idCliente);
    }
}
