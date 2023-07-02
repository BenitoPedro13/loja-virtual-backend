package com.dev.backend.repository;

import com.dev.backend.entity.PermissaoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoPessoaRepository extends JpaRepository<PermissaoPessoa, Long> {

}
