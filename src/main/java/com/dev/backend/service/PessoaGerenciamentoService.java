package com.dev.backend.service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PessoaGerenciamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;


    @Autowired
    private EmailService emailService;

    public String solicitarCodigo(String email) {
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        pessoa.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(pessoa.getId()));
        pessoa.setDataEnvioCodigo(new Date());
        pessoaRepository.saveAndFlush(pessoa);
        emailService.enviarEmailTexto(pessoa.getEmail(), "Codigo de recuperacao de senha", "Ola, o seu codigo para recuperaçao de senha e o seguinte: " + pessoa.getCodigoRecuperacaoSenha());
        return "Codigo Enviado!";
    }

    public String alterarSenha(Pessoa pessoa) {
        Pessoa pessoaBanco = pessoaRepository.findByEmailAndCodigoRecuperacaoSenha(pessoa.getEmail(), pessoa.getCodigoRecuperacaoSenha());

        if (pessoaBanco == null) {
            return "Email ou codigo nao encontrado!";
        }

        Date diferenca = new Date(new Date().getTime() - pessoaBanco.getDataEnvioCodigo().getTime());

        if (diferenca.getTime() / 1000 < 900) {
            //TODO: depois que adicionar o spring security e necessario criptografar a senha!!
            pessoaBanco.setSenha(pessoa.getSenha());
            pessoaBanco.setCodigoRecuperacaoSenha(null);
            pessoaBanco.setDataEnvioCodigo(null);
            pessoaRepository.saveAndFlush(pessoaBanco);
            return "Senha alterada com sucesso!";
        } else {
            return "Tempo expirado, solicite um novo codigo";
        }
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date()) + id;
    }


}
