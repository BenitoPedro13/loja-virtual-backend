package com.dev.backend.service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PermissaoRepository;
import com.dev.backend.repository.PessoaClienteRepository;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PessoaClienteService {

    @Autowired
    private PessoaClienteRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    public Pessoa inserir(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);

        pessoa.setDataCriacao(new Date());
        Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente((pessoaNova));
        return pessoaNova;
    }

}
