package com.fatec.comercio.forms;

import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.models.Uf;
import com.fatec.comercio.repository.UfRepository;
import lombok.Data;

@Data
public class CidadeForm {
    private Integer codcidade;
    private String nomecidade;
    private String nomeuf;

    //método de conversão para a entidade Cidade
    public Cidade converter(UfRepository ufRepository){
        Uf uf = ufRepository.findByNomeuf(nomeuf);
        return new Cidade(nomecidade, uf);
    } 
}