package com.dev.backend.controller;

import com.dev.backend.entity.Permissao;
import com.dev.backend.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Permissao> buscarTodos() {
        return permissaoService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Permissao inserir(@RequestBody Permissao permissao) {
        return permissaoService.inserir(permissao);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Permissao alterar(@RequestBody Permissao permissao) {
        return  permissaoService.alterar(permissao);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        permissaoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        permissaoService.excluirVarios(ids);
        return ResponseEntity.ok().build();
    }
}
