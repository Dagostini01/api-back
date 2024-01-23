package br.com.projeto.api.controller;

import br.com.projeto.api.model.Empresa;
import br.com.projeto.api.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private Repository acao;

    private boolean isCnpjValido(String cnpj) {
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");
        return cnpjLimpo.length() == 14;
    }

    @GetMapping("/empresas")
    public Iterable<Empresa> selecionar() {
        return acao.findAll();
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<?> selecionarid(@PathVariable long id) {
        Optional<Empresa> empresaOptional = acao.findById(id);
        if (empresaOptional.isPresent()) {
            return ResponseEntity.ok(empresaOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }
    }


    @PostMapping("/empresas")
    public ResponseEntity<?> cadastrar(@RequestBody Empresa c) {
        if (c == null || c.getNome() == null || c.getNome().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome da empresa é obrigatório");
        }
        if (c.getCnpj() != null && !isCnpjValido(c.getCnpj())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ inválido");
        }
        return ResponseEntity.ok(acao.save(c));
    }

    @PutMapping("/empresas")
    public ResponseEntity<?> editar(@RequestBody Empresa c) {
        if (c == null || c.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID da empresa é obrigatório para edição");
        }
        return ResponseEntity.ok(acao.save(c));
    }

    @DeleteMapping("/empresas/{id}")
    public void remover(@PathVariable long id){
        acao.deleteById(id);
    }

}
