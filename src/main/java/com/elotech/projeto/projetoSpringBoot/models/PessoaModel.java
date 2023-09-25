package com.elotech.projeto.projetoSpringBoot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PESSOA")
public class PessoaModel extends RepresentationModel<PessoaModel>implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPessoa;
    private String cpf;
    private String name;
   private String dataNasc;

@OneToMany
@JoinColumn(name = "ID_PERSON")
private List<ContatosModel> contacts;

    public List<ContatosModel> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContatosModel> contacts) {
        this.contacts = contacts;
    }

    public UUID getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(UUID idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
}
