package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Cep;
import com.fatec.comercio.repository.CepRepository;

@Service
public class CepService {
    @Autowired
    private CepRepository cepRepository;
    
    //Criando o construtor da classe CepService
    
    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    //Salvar todos os cep
    public List<Cep> allCeps() {
        return cepRepository.findAll();
    }

    //Buscar pelo codigo
    public Cep cepId(Integer id) {
        return cepRepository.findByCodcep(id);
    }

    //Apagar cep pelo Codigo
    public String apagaId(Integer id) {
        cepRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar cep
    public Cep salvarCep(Cep cep) {
        return cepRepository.save(cep);
    }

    //Editar cep
    public void editarCep(Integer id, Cep cep) {
        cep.setCodcep(id);
        cepRepository.save(cep);
    }
}
