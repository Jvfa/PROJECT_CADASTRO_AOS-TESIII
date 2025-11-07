package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Marca;
import com.fatec.comercio.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("")
    public ResponseEntity<List<Marca>> getMarcas() {
        return ResponseEntity.ok(marcaService.allMarcas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaId(@PathVariable Integer id) {
       return marcaService.marcaId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Marca> postMarca(@RequestBody Marca marca) {
        Marca marcaSalva = marcaService.salvarMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        marcaService.apagaId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> putMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        return marcaService.editarMarca(id, marca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}