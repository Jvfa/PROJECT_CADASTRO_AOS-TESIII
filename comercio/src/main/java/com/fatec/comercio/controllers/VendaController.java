package com.fatec.comercio.controllers;

import com.fatec.comercio.forms.VendaForm;
import com.fatec.comercio.models.Venda;
import com.fatec.comercio.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("")
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaForm form) {
        try {
            Venda novaVenda = vendaService.salvarVenda(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
        } catch (RuntimeException e) {
            // Se o service lançar uma exceção (ex: cliente ou produto não encontrado), retorna um erro.
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public List<Venda> getTodasVendas() {
        return vendaService.buscarTodasVendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getVendaPorId(@PathVariable Integer id) {
        Venda venda = vendaService.buscarVendaPorId(id);
        if (venda != null) {
            return ResponseEntity.ok(venda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}