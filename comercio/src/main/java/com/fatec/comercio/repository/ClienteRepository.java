package com.fatec.comercio.repository;

import com.fatec.comercio.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // Metodo para buscar um cliente pelo código (já vem por padrão, mas podemos explicitar)
    Cliente findByCodcliente(Integer codcliente);

    // Exemplo de metodo de busca customizado que podemos adicionar
    Cliente findByCpf(String cpf);

}