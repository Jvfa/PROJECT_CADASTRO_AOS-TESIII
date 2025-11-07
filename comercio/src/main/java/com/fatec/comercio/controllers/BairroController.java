package com.fatec.comercio.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fatec.comercio.models.Bairro;
import com.fatec.comercio.service.BairroService;

@RestController
@RequestMapping("/bairros")
public class BairroController {
    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<Bairro>> getBairros() {
        return ResponseEntity.ok(bairroService.allBairros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bairro> getBairroId(@PathVariable Integer id) {
       return bairroService.bairroId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Bairro> postBairro(@RequestBody Bairro bairro) {
        Bairro bairroSalvo = bairroService.salvarBairro(bairro);
        return ResponseEntity.status(HttpStatus.CREATED).body(bairroSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaid(@PathVariable Integer id) {
        bairroService.apagaId(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Bairro> putBairro(@PathVariable Integer id, @RequestBody Bairro bairro) {
        return bairroService.editarBairro(id, bairro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}