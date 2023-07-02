package com.dev.backend.service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PessoaClienteService {

    @Autowired
    private PessoaClienteRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa inserir(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);

        pessoa.setDataCriacao(new Date());
        Pessoa pessoaNova = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente((pessoaNova));
        // emailService.enviarEmailTexto(pessoaNova.getEmail(), "Cadastro na Loja Concluido.", "O registro na loja foi realizado com sucesso!. Em breve voce recebera a senha de acesso por E-Mail.");
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("nome", pessoaNova.getNome());
        propMap.put("mensagem", "O registro na loja foi realizado com sucesso!. Em breve voce recebera a senha de acesso por E-Mail.");
        emailService.enviarEmailTemplate(pessoaNova.getEmail(), "Cadastro na Loja Concluido.", propMap);
        return pessoaNova;
    }


}
