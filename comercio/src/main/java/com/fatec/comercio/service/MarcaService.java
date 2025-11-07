package com.fatec.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.comercio.models.Marca;
import com.fatec.comercio.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;
    
    //Criando o construtor da classe marcaService
    
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    //Salvar todos as Marcas
    public List<Marca> allMarcas() {
        return marcaRepository.findAll();
    }

    //Buscar pelo codigo
    public Marca marcaId(Integer id) {
        return marcaRepository.findByCodmarca(id);
    }

    //Apagar Marca pelo Codigo
    public String apagaId(Integer id) {
        marcaRepository.deleteById(id);
        return "Apaguei: " + id;
    }

    //Salvar Marca
    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    //Editar Marca
    public void editarMarca(Integer id, Marca marca) {
        marca.setCodmarca(id);
        marcaRepository.save(marca);
    }
}
