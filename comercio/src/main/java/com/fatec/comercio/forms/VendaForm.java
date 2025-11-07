package com.fatec.comercio.forms;

import java.util.List;
import lombok.Data;

@Data
public class VendaForm {
    private Integer clienteId;
    private List<ItemVendaForm> itens;
}