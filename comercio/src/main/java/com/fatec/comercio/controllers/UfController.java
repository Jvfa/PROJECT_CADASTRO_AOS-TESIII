package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Uf;
import com.fatec.comercio.service.UfService;

@RestController
@RequestMapping("/ufs")
public class UfController {

    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @GetMapping("")
    public ResponseEntity<List<Uf>> getUfs() {
        return ResponseEntity.ok(ufService.allUfs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Uf> getUfId(@PathVariable Integer id) {
       return  ufService.ufId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("")
    public ResponseEntity<Uf> postUf(@RequestBody Uf uf) {
        Uf ufSalva = ufService.salvarUf(uf);
        return ResponseEntity.status(HttpStatus.CREATED).body(ufSalva);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaId(@PathVariable Integer id){
        ufService.apagaId(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Uf> putUf(@PathVariable Integer id, @RequestBody Uf uf) {
        return ufService.editarUf(id, uf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}