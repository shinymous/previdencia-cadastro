package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Deposito;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DepositoRepository extends CrudRepository<Deposito, Long>, JpaSpecificationExecutor {

    @Query(value = "select sum(d.valor) from Deposito d " +
            "where d.contaId = ?1 ")
    Optional<BigDecimal> findSomaByConta(Long contaId);

    @Query(value = "select d from Deposito d " +
            "join d.conta c " +
            "where c.contaTipo = 'Conta Comum' " +
            "and c.usuario.id = ?1 " +
            "order by d.data ")
    List<Deposito> findAllByUsuarioIdAndContaComum(Long usuarioId);

    @Query(value = "select d from Deposito d " +
            "join d.conta c " +
            "where c.contaTipo = 'Conta Eventual' " +
            "and c.usuario.id = ?1 " +
            "order by d.data ")
    List<Deposito> findAllByUsuarioIdAndContaEventual(Long usuarioId);
}
