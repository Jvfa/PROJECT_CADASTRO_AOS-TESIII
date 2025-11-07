package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Rua;
import com.fatec.comercio.service.RuaService;

@RestController
@RequestMapping("/ruas")
public class RuaController {
    private final RuaService ruaService;

    public RuaController(RuaService ruaService) {
        this.ruaService = ruaService;
    }

    @GetMapping("")
    public ResponseEntity<List<Rua>> getRuas() {
        return ResponseEntity.ok(ruaService.allRuas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rua> getRuaId(@PathVariable Integer id) {
       return ruaService.ruaId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Rua> postRua(@RequestBody Rua rua) {
        Rua ruaSalva = ruaService.salvarRua(rua);
        return ResponseEntity.status(HttpStatus.CREATED).body(ruaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        ruaService.apagaId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rua> putRua(@PathVariable Integer id, @RequestBody Rua rua) {
        return ruaService.editarRua(id, rua)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}