package com.elotech.projeto.projetoSpringBoot.repositories;

import com.elotech.projeto.projetoSpringBoot.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> {

}
