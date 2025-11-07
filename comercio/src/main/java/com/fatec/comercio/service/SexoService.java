package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.repository.SexoRepository;

@Service
public class SexoService {
    
    @Autowired
    private SexoRepository sexoRepository;
    
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    public List<Sexo> allSexos() {
        return sexoRepository.findAll();
    }

    public Optional<Sexo> sexoId(Integer id) {
        return sexoRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        sexoRepository.deleteById(id);
    }

    public Sexo salvarSexo(Sexo sexo) {
        return sexoRepository.save(sexo);
    }

    public Optional<Sexo> editarSexo(Integer id, Sexo sexoAtualizado) {
         return sexoRepository.findById(id)
            .map(sexoExistente -> {
                sexoAtualizado.setCodsexo(id);
                return sexoRepository.save(sexoAtualizado);
            });
    }
}