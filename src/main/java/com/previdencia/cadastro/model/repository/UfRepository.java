package com.previdencia.cadastro.model.repository;

import com.previdencia.cadastro.model.entity.Uf;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UfRepository extends CrudRepository<Uf, Long>, JpaSpecificationExecutor {

    @Query(value = "select uf from Uf uf where " +
            "uf.descricao = ?1 or uf.iniciais = ?1 ")
    Optional<Uf> findByDescricaoOuIniciais(String parametro);
}
