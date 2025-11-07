package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.repository.SexoRepository;

@Service
public class SexoService {
    //injeção de dependência

    @Autowired
    private SexoRepository sexoRepository;
    
    //Criando o construtor da classe SexoService
    
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    //Salvar todos os Sexo
    public List<Sexo> allSexos() {
        return sexoRepository.findAll();
    }

    //Buscar pelo codigo
    public Sexo sexoId(Integer id) {
        return sexoRepository.findByCodsexo(id);
    }

    //Apagar sexo pelo Codigo
    public String apagaId(Integer id) {
        sexoRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar sexo
    public Sexo salvarSexo(Sexo sexo) {
        return sexoRepository.save(sexo);
    }

    //Editar sexo
    public void editarSexo(Integer id, Sexo sexo) {
        sexo.setCodsexo(id);
        sexoRepository.save(sexo);
    }

    

}
