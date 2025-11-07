package com.fatec.comercio.service;

import com.fatec.comercio.forms.CidadeForm;
import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.models.Uf;
import com.fatec.comercio.repository.CidadeRepository;
import com.fatec.comercio.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UfRepository ufRepository;

    public CidadeService(CidadeRepository cidadeRepository, UfRepository ufRepository){
        this.cidadeRepository = cidadeRepository;
        this.ufRepository = ufRepository;
    }

    public List<Cidade> allCidades(){
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> cidadeId(Integer id){
        return cidadeRepository.findById(id); // <--- CORRIGIDO
    }

    public Optional<Cidade> cidadeNomecidade(String nomecidade){
        return Optional.ofNullable(cidadeRepository.findByNomecidade(nomecidade));
    }

    public void apagaCidadeId(Integer id){
        cidadeRepository.deleteById(id);
    }

    public Cidade salvarCidade(CidadeForm cidadeForm){
        Cidade cidade = cidadeForm.converter(ufRepository);
        return cidadeRepository.save(cidade);
    }

    public Optional<Cidade> editaCidade(CidadeForm cidadeForm, Integer id){
        Uf uf = ufRepository.findByNomeuf(cidadeForm.getNomeuf());
        
        return cidadeRepository.findById(id) // Usando findById
                .map(cidade -> {
                    cidade.setNomecidade(cidadeForm.getNomecidade());
                    cidade.setUf(uf);
                    return cidadeRepository.save(cidade); // Salva a entidade atualizada
                });
    }
}