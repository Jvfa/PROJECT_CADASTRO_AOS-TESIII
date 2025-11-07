package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Sexo;
import com.fatec.comercio.service.SexoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/sexos")
public class SexoController {
    private final SexoService sexoService;

    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }
    
    @GetMapping("")
    public List<Sexo> getSexos() {
        return sexoService.allSexos();
    }

    @GetMapping("/{id}")
    public Sexo getSexoId(@PathVariable Integer id) {
       return  sexoService.sexoId(id);
    }

    @PostMapping("")
    public String postSexo(@RequestBody Sexo sexo) {
        sexoService.salvarSexo(sexo);
        return "Dados Salvos com Sucesso!!!";
    }

    @DeleteMapping("/{id}")
    public String deletaid(@PathVariable Integer id) {
        sexoService.apagaId(id);
        return "O Sexo de codigo: " + id + " foi deletado";
    }
    
    @PutMapping("/{id}")
    public String putSexo(@PathVariable Integer id, @RequestBody Sexo sexo) {
        sexoService.editarSexo(id, sexo);
        return "Dados Atualizado com sucesso";
    }
}
