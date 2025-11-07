package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codcliente;

    @Column
    private String nomecliente;

    @Column
    private String cpf;

    @Column
    private Date datanasc;

    @Column
    private String numerocasa;

    @ManyToOne
    @JoinColumn(name = "codsexofk")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "codcidadefk")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "codcepfk")
    private Cep cep;

    @ManyToOne
    @JoinColumn(name = "codbairrofk")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "codruafk")
    private Rua rua;
}