package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Tipo;
import com.fatec.comercio.service.TipoService;

@RestController
@RequestMapping("/tipos")
public class TipoController {
    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

     @GetMapping("")
     public ResponseEntity<List<Tipo>> getTipos() {
         return ResponseEntity.ok(tipoService.allTipos());
     }
    
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipoId(@PathVariable Integer id) {
       return tipoService.tipoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Tipo> postTipo(@RequestBody Tipo tipo) {
        Tipo tipoSalvo = tipoService.salvarTipo(tipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        tipoService.apagaId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> putTipo(@PathVariable Integer id, @RequestBody Tipo tipo) {
        return tipoService.editarTipo(id, tipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}