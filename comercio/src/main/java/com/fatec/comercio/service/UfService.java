package com.fatec.comercio.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.comercio.models.Uf;
import com.fatec.comercio.repository.UfRepository;

@Service
public class UfService {
    @Autowired 
    private UfRepository ufRepository;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public List<Uf> allUfs(){
        return ufRepository.findAll();
    }

    public Optional<Uf> ufId(Integer id){
        return ufRepository.findById(id); // <--- CORRIGIDO
    }

    public void apagaId(Integer id){
        ufRepository.deleteById(id);
    }

    public Uf salvarUf(Uf uf){
        return ufRepository.save(uf);
    }

    public Optional<Uf> editarUf(Integer id, Uf ufAtualizada){
        return ufRepository.findById(id)
            .map(ufExistente -> {
                 ufAtualizada.setCoduf(id);
                return ufRepository.save(ufAtualizada);
            });
    }
}