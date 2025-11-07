package com.fatec.comercio.controllers;

import com.fatec.comercio.forms.CidadeForm;
import com.fatec.comercio.models.Cidade;
import com.fatec.comercio.service.CidadeService;
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
    public List<Cidade> getCidades(){
        return cidadeService.allCidades();
    }

    @GetMapping("/{id}")
    public Cidade getCidadeId(@RequestParam Integer id){
        return cidadeService.cidadeId(id);
    }

    @GetMapping("/{nomecidade}")
    public Cidade getNomecidade(@RequestParam String nomecidade){
        return cidadeService.cidadeNomecidade(nomecidade);
    }

    @DeleteMapping("/{id}")
    public void deleteCidadeId(@RequestParam Integer id){
        cidadeService.apagaCidadeId(id);
    }

    @PutMapping("/{id}")
    public Cidade putCidadeId(@PathVariable Integer id, @RequestBody CidadeForm cidadeForm){
        return cidadeService.editaCidade(cidadeForm, id);
    }

    @PostMapping("")
    public String postCidade(@RequestBody CidadeForm cidadeForm){
        cidadeService.salvarCidade(cidadeForm);
        return "Sucesso!";
    }

}
