package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Cep;
import com.fatec.comercio.service.CepService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/ceps")
public class CepController {
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("")
    public ResponseEntity<List<Cep>> getCeps() {
        return ResponseEntity.ok(cepService.allCeps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cep> getCepId(@PathVariable Integer id) {
       return cepService.cepId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("")
    public ResponseEntity<Cep> postCep(@RequestBody Cep cep) {
        Cep cepSalvo = cepService.salvarCep(cep);
        return ResponseEntity.status(HttpStatus.CREATED).body(cepSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        cepService.apagaId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cep> putCep(@PathVariable Integer id, @RequestBody Cep cep) {
        return cepService.editarCep(id, cep)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}