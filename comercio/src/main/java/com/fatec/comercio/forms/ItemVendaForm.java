package com.fatec.comercio.forms;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemVendaForm {
    private Integer produtoId;
    private BigDecimal quantidade;
}