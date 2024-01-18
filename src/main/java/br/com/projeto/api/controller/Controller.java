package br.com.projeto.api.controller;

import br.com.projeto.api.model.Empresa;
import br.com.projeto.api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private Repository acao;

    @PostMapping("/")
    public Empresa cadastrar(@RequestBody Empresa c){
        return acao.save(c);
    }

    @GetMapping("/")
    public Iterable<Empresa> selecionar() {
        return acao.findAll();
    }

    @PutMapping("/")
    public Empresa editar(@RequestBody Empresa c){
        return acao.save(c);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id){
        acao.deleteById(id);
    }

}
