package com.fatec.comercio.models;

import java.util.List;
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
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coduf;

    @Column 
    private String nomeuf;

    @Column
    private String sigla;

    @OneToMany(mappedBy = "uf")
    private List<Cidade> cidades;
}