package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  

import com.fatec.comercio.models.Bairro;
import com.fatec.comercio.repository.BairroRepository;

@Service
public class BairroService {
    @Autowired
    private BairroRepository bairroRepository;
    
    //Criando o construtor da classe BairroService
    
    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    //Salvar todos os bairros
    public List<Bairro> allBairros() {
        return bairroRepository.findAll();
    }

    //Buscar pelo codigo
    public Bairro bairroId(Integer id) {
        return bairroRepository.findByCodbairro(id);
    }

    //Apagar bairro pelo Codigo
    public String apagaId(Integer id) {
        bairroRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar bairro
    public Bairro salvarBairro(Bairro bairro) {
        return bairroRepository.save(bairro);
    }

    //Editar bairro
    public void editarBairro(Integer id, Bairro bairro) {
        bairro.setCodbairro(id);
        bairroRepository.save(bairro);
    }


}
