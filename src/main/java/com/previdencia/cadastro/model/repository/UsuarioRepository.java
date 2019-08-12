package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>, JpaSpecificationExecutor {

    Page<Usuario> findAll(Pageable pageable);
}
