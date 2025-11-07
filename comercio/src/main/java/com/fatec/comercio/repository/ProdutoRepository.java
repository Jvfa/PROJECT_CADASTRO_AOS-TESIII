package com.fatec.comercio.repository;

import com.fatec.comercio.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Ele permitirá buscar um produto pelo nome.
    Produto findByNomeproduto(String nomeproduto);

}
