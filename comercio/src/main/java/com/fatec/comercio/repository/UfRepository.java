package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Uf;
import java.util.List;


public interface UfRepository extends JpaRepository<Uf, Integer>{

    Uf findByNomeuf(String nomeuf);
    Uf findBySigla(String sigla);
}
