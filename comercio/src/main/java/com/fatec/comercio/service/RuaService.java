package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Rua;
import com.fatec.comercio.repository.RuaRepository;

@Service
public class RuaService {
    @Autowired
    private RuaRepository ruaRepository;
    
    //Criando o construtor da classe CepService
    
    public RuaService(RuaRepository ruaRepository) {
        this.ruaRepository = ruaRepository;
    }

    //Salvar todos as Ruas
    public List<Rua> allRuas() {
        return ruaRepository.findAll();
    }

    //Buscar pelo codigo
    public Rua ruaId(Integer id) {
        return ruaRepository.findByCodrua(id);
    }

    //Apagar Rua pelo Codigo
    public String apagaId(Integer id) {
        ruaRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar Rua
    public Rua salvarRua(Rua rua) {
        return ruaRepository.save(rua);
    }

    //Editar cep
    public void editarRua(Integer id, Rua rua) {
        rua.setCodrua(id);
        ruaRepository.save(rua);
    }
}
