package com.dev.backend.controller;

import com.dev.backend.entity.Estado;
import com.dev.backend.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Estado> buscarTodos() {
        return estadoService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Estado inserir(@RequestBody Estado estado) {
        return estadoService.inserir(estado);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Estado alterar(@RequestBody Estado estado) {
        return  estadoService.alterar(estado);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        estadoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        estadoService.excluirVarios(ids);
        return ResponseEntity.ok().build();
    }

}
