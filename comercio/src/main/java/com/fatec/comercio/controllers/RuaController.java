package com.fatec.comercio.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public List<Rua> getRuas() {
        return ruaService.allRuas();
    }

    @GetMapping("/{id}")
    public Rua getRuaId(@PathVariable Integer id) {
       return ruaService.ruaId(id);
    }

    @PostMapping("")
    public String postRua(@RequestBody Rua rua) {
        ruaService.salvarRua(rua);
        return "Dados Salvos com Sucesso!!!";
    }

    @DeleteMapping("/{id}")
    public String deletaid(@PathVariable Integer id) {
        ruaService.apagaId(id);
        return "A Rua de codigo: " + id + " foi deletado";
    }

    @PutMapping("/{id}")
    public String putRua(@PathVariable Integer id, @RequestBody Rua rua) {
        ruaService.editarRua(id, rua);
        return "Dados Atualizado com sucesso";
    }
}
