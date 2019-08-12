package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Uf;
import com.previdencia.cadastro.model.entity.UsuarioEndereco;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioEnderecoRepository extends CrudRepository<UsuarioEndereco, Long>, JpaSpecificationExecutor {

    Optional<UsuarioEndereco> findByUsuarioId(Long id);
}
