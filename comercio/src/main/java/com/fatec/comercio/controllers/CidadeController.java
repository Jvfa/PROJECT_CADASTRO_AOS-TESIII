package com.fatec.comercio.controllers;

import com.fatec.comercio.forms.CidadeForm;
import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    CidadeController(CidadeService cidadeService){
        this.cidadeService = cidadeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Cidade>> getCidades(){
        return ResponseEntity.ok(cidadeService.allCidades());
    }

    // Corrigido: Usando @PathVariable para "/{id}"
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getCidadeId(@PathVariable Integer id){
        return cidadeService.cidadeId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Corrigido: Usando @PathVariable para "/{nomecidade}"
    @GetMapping("/nome/{nomecidade}") // Mudei a rota para evitar ambiguidade com /:id
    public ResponseEntity<Cidade> getNomecidade(@PathVariable String nomecidade){
        return cidadeService.cidadeNomecidade(nomecidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Corrigido: Usando @PathVariable para "/{id}"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCidadeId(@PathVariable Integer id){
        cidadeService.apagaCidadeId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> putCidadeId(@PathVariable Integer id, @RequestBody CidadeForm cidadeForm){
        return cidadeService.editaCidade(cidadeForm, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Cidade> postCidade(@RequestBody CidadeForm cidadeForm){
        Cidade cidadeSalva = cidadeService.salvarCidade(cidadeForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
    }
}