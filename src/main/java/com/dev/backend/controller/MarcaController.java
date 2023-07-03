package com.dev.backend.controller;

import com.dev.backend.entity.Marca;
import com.dev.backend.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Marca> buscarTodos() {
        return marcaService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Marca inserir(@RequestBody Marca marca) {
        return marcaService.inserir(marca);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Marca alterar(@RequestBody Marca marca) {
        return  marcaService.alterar(marca);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        marcaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/batch")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluirVarios(@RequestBody List<Long> ids) {
        marcaService.excluirVarios(ids);
        return ResponseEntity.ok().build();
    }
}
