package com.elotech.projeto.projetoSpringBoot.repositories;

import com.elotech.projeto.projetoSpringBoot.models.ContatosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactsRepository extends JpaRepository<ContatosModel, UUID> {

}
