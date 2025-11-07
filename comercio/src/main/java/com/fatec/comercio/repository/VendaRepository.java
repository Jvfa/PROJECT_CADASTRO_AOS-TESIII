package com.fatec.comercio.repository;

import com.fatec.comercio.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Date;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    // Exemplo de consulta customizada para buscar vendas por data
    List<Venda> findByDatavenda(Date data);

    // Exemplo de consulta para buscar vendas de um cliente específico pelo ID do cliente
    List<Venda> findByClienteCodcliente(Integer codcliente);

}
