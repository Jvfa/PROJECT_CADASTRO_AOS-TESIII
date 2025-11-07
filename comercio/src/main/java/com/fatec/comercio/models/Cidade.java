package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Getter
@Setter
@NoArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codcidade;

    @Column
    private String nomecidade;

    @ManyToOne
    @JoinColumn(name = "coduffk")
    private Uf uf;

    // Construtor usado pelo CidadeForm
    public Cidade(String nomecidade, Uf uf){
        this.nomecidade = nomecidade;
        this.uf = uf;
    }
}