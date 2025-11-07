package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Bairro;
import com.fatec.comercio.repository.BairroRepository;

@Service
public class BairroService {
    
    @Autowired
    private BairroRepository bairroRepository;
    
    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    public List<Bairro> allBairros() {
        return bairroRepository.findAll();
    }

    public Optional<Bairro> bairroId(Integer id) {
        return bairroRepository.findById(id); // Alterado para findById
    }

    public void apagaId(Integer id) {
        bairroRepository.deleteById(id);
    }

    public Bairro salvarBairro(Bairro bairro) {
        return bairroRepository.save(bairro);
    }

    public Optional<Bairro> editarBairro(Integer id, Bairro bairroAtualizado) {
        return bairroRepository.findById(id)
            .map(bairroExistente -> {
                bairroAtualizado.setCodbairro(id);
                return bairroRepository.save(bairroAtualizado);
            });
    }
}