package com.fatec.comercio.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoId implements Serializable {

    private Integer codvendafk;
    private Integer codprodutofk;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaProdutoId that = (VendaProdutoId) o;
        return Objects.equals(codvendafk, that.codvendafk) &&
                Objects.equals(codprodutofk, that.codprodutofk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codvendafk, codprodutofk);
    }
}