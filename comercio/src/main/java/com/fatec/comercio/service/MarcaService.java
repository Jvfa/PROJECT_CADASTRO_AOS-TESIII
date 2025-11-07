package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Marca;
import com.fatec.comercio.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;
    
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> allMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> marcaId(Integer id) {
        return marcaRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        marcaRepository.deleteById(id);
    }

    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Optional<Marca> editarMarca(Integer id, Marca marcaAtualizada) {
        return marcaRepository.findById(id)
            .map(marcaExistente -> {
                marcaAtualizada.setCodmarca(id);
                return marcaRepository.save(marcaAtualizada);
            });
    }
}