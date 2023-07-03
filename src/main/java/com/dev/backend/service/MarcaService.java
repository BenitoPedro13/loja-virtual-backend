package com.dev.backend.service;

import com.dev.backend.entity.Marca;
import com.dev.backend.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> buscarTodos(){
        return marcaRepository.findAll();
    }

    public Marca inserir(Marca marca){
        marca.setDataCriacao(new Date());
        return marcaRepository.saveAndFlush(marca);
    }

    public Marca alterar(Marca marca){
        marca.setDataAtualizacao(new Date());
        return marcaRepository.saveAndFlush(marca);
    }

    public void excluir(Long id){
        boolean marcaExiste = marcaRepository.findById(id).isPresent();

        if (!marcaExiste) {
            return;
        }

        Marca marca = marcaRepository.findById(id).get();
        marcaRepository.delete(marca);
    }

    public void excluirVarios(List<Long> ids){
        ids.forEach(this::excluir);
    }

}
