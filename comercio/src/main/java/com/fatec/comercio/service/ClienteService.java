package com.fatec.comercio.service;

import com.fatec.comercio.models.Cliente;
import com.fatec.comercio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepository.findById(id); // Usando findById padrão
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> editarCliente(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteAtualizado.setCodcliente(id);
                    return clienteRepository.save(clienteAtualizado);
                });
    }

    public void apagarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}