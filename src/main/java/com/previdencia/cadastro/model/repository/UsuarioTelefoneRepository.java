package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.UsuarioTelefone;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioTelefoneRepository extends CrudRepository<UsuarioTelefone, Long>, JpaSpecificationExecutor {

    Optional<UsuarioTelefone> findByUsuarioId(Long id);
}
