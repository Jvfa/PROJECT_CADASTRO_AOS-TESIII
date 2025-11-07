package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.repository.TipoRepository;

@Service
public class TipoService {
     @Autowired
    private TipoRepository tipoRepository;
    
    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<Tipo> allTipos() {
        return tipoRepository.findAll();
    }

    public Optional<Tipo> tipoId(Integer id) {
        return tipoRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        tipoRepository.deleteById(id);
    }

    public Tipo salvarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }

    public Optional<Tipo> editarTipo(Integer id, Tipo tipoAtualizado) {
        return tipoRepository.findById(id)
            .map(tipoExistente -> {
                tipoAtualizado.setCodtipo(id);
                return tipoRepository.save(tipoAtualizado);
            });
    }
}