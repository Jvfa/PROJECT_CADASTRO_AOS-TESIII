package com.fatec.comercio.repository;

import com.fatec.comercio.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
    public Cidade findByNomecidade(String nomecidade);


}
