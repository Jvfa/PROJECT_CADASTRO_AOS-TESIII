package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Getter
@Setter
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codproduto;

    @Column
    private String nomeproduto;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(precision = 10, scale = 2)
    private BigDecimal quantidade;

    @ManyToOne
    @JoinColumn(name = "codmarcafk")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "codtipofk")
    private Tipo tipo;
}