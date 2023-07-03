package com.dev.backend.controller;

import com.dev.backend.entity.Cidade;
import com.dev.backend.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Cidade> buscarTodos() {
        return cidadeService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Cidade inserir(@RequestBody Cidade cidade) {
        return cidadeService.inserir(cidade);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Cidade alterar(@RequestBody Cidade cidade) {
        return  cidadeService.alterar(cidade);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        cidadeService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        cidadeService.excluirVarios(ids);
        return ResponseEntity.ok().build();
    }
}
