package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VendaProduto {

    @EmbeddedId
    private VendaProdutoId id;

    @ManyToOne
    @MapsId("codvendafk")
    @JoinColumn(name = "codvendafk")
    @JsonIgnore
    private Venda venda;

    @ManyToOne
    @MapsId("codprodutofk")
    @JoinColumn(name = "codprodutofk")
    private Produto produto;

    @Column(name = "quantv", precision = 10, scale = 2)
    private BigDecimal quantidadeVendida;

    @Column(name = "valorv", precision = 10, scale = 2)
    private BigDecimal valorVenda;
}