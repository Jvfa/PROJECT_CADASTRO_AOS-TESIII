package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.service.SexoService;

@RestController
@RequestMapping("/sexos")
public class SexoController {
    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<Sexo>> getSexos() {
        return ResponseEntity.ok(sexoService.allSexos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sexo> getSexoId(@PathVariable Integer id) {
       return  sexoService.sexoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Sexo> postSexo(@RequestBody Sexo sexo) {
        Sexo sexoSalvo = sexoService.salvarSexo(sexo);
        return ResponseEntity.status(HttpStatus.CREATED).body(sexoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        sexoService.apagaId(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Sexo> putSexo(@PathVariable Integer id, @RequestBody Sexo sexo) {
        return sexoService.editarSexo(id, sexo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}