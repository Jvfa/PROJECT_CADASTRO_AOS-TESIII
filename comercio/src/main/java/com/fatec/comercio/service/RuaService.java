package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Rua;
import com.fatec.comercio.repository.RuaRepository;

@Service
public class RuaService {
    @Autowired
    private RuaRepository ruaRepository;
    
    public RuaService(RuaRepository ruaRepository) {
        this.ruaRepository = ruaRepository;
    }

    public List<Rua> allRuas() {
        return ruaRepository.findAll();
    }

    public Optional<Rua> ruaId(Integer id) {
        return ruaRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        ruaRepository.deleteById(id);
    }

    public Rua salvarRua(Rua rua) {
        return ruaRepository.save(rua);
    }

    public Optional<Rua> editarRua(Integer id, Rua ruaAtualizada) {
         return ruaRepository.findById(id)
            .map(ruaExistente -> {
                ruaAtualizada.setCodrua(id);
                return ruaRepository.save(ruaAtualizada);
            });
    }
}