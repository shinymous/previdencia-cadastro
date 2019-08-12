package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Long>, JpaSpecificationExecutor {

    @Query(value = "select c.id from Conta c " +
            "where c.usuario = ?1 " +
            "and c.contaTipo = 'Conta Comum' ")
    Optional<Long> findContaComumByUsuarioId(Long usuarioId);

    @Query(value = "select c.id from Conta c " +
            "where c.usuario = ?1 " +
            "and c.contaTipo = 'Conta Eventual' ")
    Optional<Long> findContaEventualByUsuarioId(Long usuarioId);

    @Query(value = "select c from Conta c " +
            "where c.usuario.id = ?1 ")
    List<Conta> findAllContaByUsuarioId(Long id);
}
