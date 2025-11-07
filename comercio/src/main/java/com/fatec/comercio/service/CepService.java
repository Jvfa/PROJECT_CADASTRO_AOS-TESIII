package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Cep;
import com.fatec.comercio.repository.CepRepository;

@Service
public class CepService {
    @Autowired
    private CepRepository cepRepository;
    
    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public List<Cep> allCeps() {
        return cepRepository.findAll();
    }

    public Optional<Cep> cepId(Integer id) {
        return cepRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        cepRepository.deleteById(id);
    }

    public Cep salvarCep(Cep cep) {
        return cepRepository.save(cep);
    }

    public Optional<Cep> editarCep(Integer id, Cep cepAtualizado) {
         return cepRepository.findById(id)
            .map(cepExistente -> {
                cepAtualizado.setCodcep(id);
                return cepRepository.save(cepAtualizado);
            });
    }
}