package com.fatec.comercio.service;

import com.fatec.comercio.models.Cliente;
import com.fatec.comercio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Metodo para buscar todos os clientes
    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    // Metodo para buscar um cliente pelo ID
    public Cliente buscarClientePorId(Integer id) {
        return clienteRepository.findByCodcliente(id);
    }

    // Metodo para salvar um novo cliente
    public Cliente salvarCliente(Cliente cliente) {
        // Aqui você pode adicionar validações e regras de negócio antes de salvar
        return clienteRepository.save(cliente);
    }

    // Metodo para editar um cliente existente
    public void editarCliente(Integer id, Cliente cliente) {
        // Garante que estamos atualizando o cliente com o ID correto
        cliente.setCodcliente(id);
        clienteRepository.save(cliente);
    }

    // Metodo para apagar um cliente pelo ID
    public void apagarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
