package com.fatec.comercio.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.comercio.models.Cep;
import com.fatec.comercio.service.CepService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ceps")
public class CepController {
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("")
    public List<Cep> getCeps() {
        return cepService.allCeps();
    }

    @GetMapping("/{id}")
    public Cep getCepId(@PathVariable Integer id) {
       return cepService.cepId(id);
    }
    
    @PostMapping("")
    public String postCep(@RequestBody Cep cep) {
        cepService.salvarCep(cep);
        return "Dados Salvos com Sucesso!!!";
    }

    @DeleteMapping("/{id}")
    public String deletaid(@PathVariable Integer id) {
        cepService.apagaId(id);
        return "O CEP de codigo: " + id + " foi deletado";
    }

    @PutMapping("/{id}")
    public String putCep(@PathVariable Integer id, @RequestBody Cep cep) {
        cepService.editarCep(id, cep);
        return "Dados Atualizado com sucesso";
    }
}
