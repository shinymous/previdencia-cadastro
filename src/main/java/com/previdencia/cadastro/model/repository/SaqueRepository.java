package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Saque;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SaqueRepository extends CrudRepository<Saque, Long>, JpaSpecificationExecutor {

    @Query(value = "select sum(s.valor) from Saque s " +
            "where s.contaId = ?1 ")
    Optional<BigDecimal> findSomaByConta(Long contaId);

    @Query(value = "select s from Saque s " +
            "join s.conta c " +
            "where c.contaTipo = 'Conta Comum' " +
            "and c.usuario.id = ?1 " +
            "order by s.data ")
    List<Saque> findAllByUsuarioIdAndContaComum(Long usuarioId);

    @Query(value = "select s from Saque s " +
            "join s.conta c " +
            "where c.contaTipo = 'Conta Eventual' " +
            "and c.usuario.id = ?1 " +
            "order by s.data")
    List<Saque> findAllByUsuarioIdAndContaEventual(Long usuarioId);
}
