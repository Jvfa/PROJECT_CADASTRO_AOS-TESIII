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
    public ResponseEntity<?> criarVenda(@RequestBody VendaForm form) { // Alterado para ResponseEntity<?>
        try {
            Venda novaVenda = vendaService.salvarVenda(form);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
        } catch (RuntimeException e) {
            // Retorna o erro de negócio (ex: estoque insuficiente)
            return ResponseEntity.badRequest().body(e.getMessage());
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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVenda(@PathVariable Integer id) {
        try {
            vendaService.apagarVenda(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content (Sucesso)
        } catch (RuntimeException e) {
            // Se a VendaService lançar a exceção (Venda não encontrada)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}