package com.elotech.projeto.projetoSpringBoot.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CONTATOS")
public class ContatosModel implements Serializable {

   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
   private String name;
   private String fone;
   private String email;

   @ManyToOne
   @JoinColumn(name = "ID_PERSON")
   @JsonIgnore
   private PessoaModel person;

    public ContatosModel() {
    }

    public ContatosModel(String name, String fone, String email, PessoaModel person) {
        this.name = name;
        this.fone = fone;
        this.email = email;
        this.person = person;
    }

    public PessoaModel getPerson() {
        return person;
    }

    public void setPerson(PessoaModel person) {
        this.person = person;
    }

    public UUID getId() {
        return id;
    }



    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
