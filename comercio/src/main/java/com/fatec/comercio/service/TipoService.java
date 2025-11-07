package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.repository.TipoRepository;

@Service
public class TipoService {
     @Autowired
    private TipoRepository tipoRepository;
    
    //Criando o construtor da classe marcaService
    
    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    //Salvar todos as Marcas
    public List<Tipo> allTipos() {
        return tipoRepository.findAll();
    }

    //Buscar pelo codigo
    public Tipo tipoId(Integer id) {
        return tipoRepository.findByCodtipo(id);
    }

    //Apagar Marca pelo Codigo
    public String apagaId(Integer id) {
        tipoRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar Marca
    public Tipo salvarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    //Editar Marca
    public void editarTipo(Integer id, Tipo tipo) {
        tipo.setCodtipo(id);
        tipoRepository.save(tipo);
    }
}
