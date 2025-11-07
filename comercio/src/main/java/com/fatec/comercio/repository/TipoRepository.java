package com.fatec.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.comercio.models.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, Integer>{
    Tipo findByCodtipo(Integer id);
    Tipo findByNometipo(String nometipo);
}
