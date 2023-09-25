package com.elotech.projeto.projetoSpringBoot.controllers;

import com.elotech.projeto.projetoSpringBoot.dtos.ContatoRecordDto;
import com.elotech.projeto.projetoSpringBoot.models.ContatosModel;
import com.elotech.projeto.projetoSpringBoot.repositories.ContactsRepository;
import com.elotech.projeto.projetoSpringBoot.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ContactsController {

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping("/contatos/post")
    public ResponseEntity<ContatosModel> save(
                                              @RequestBody @Valid ContatoRecordDto contatoRecordDto){

        var contatosModel =  new ContatosModel();
        BeanUtils.copyProperties(contatoRecordDto,contatosModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactsRepository.save(contatosModel));
    }



    @GetMapping("/contatos/get")
    public ResponseEntity<List<ContatosModel>> getAllContacts(){

        List<ContatosModel> contatosModelList = contactsRepository.findAll();

            return ResponseEntity.status(HttpStatus.OK).body(contatosModelList);



    }

    @GetMapping("/contatos/get/{id}")
    public ResponseEntity<Object> getOneContact(@PathVariable(value = "id")UUID id){
        Optional<ContatosModel> contact0 = contactsRepository.findById(id);
        if(contact0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não Encontrado.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(contact0.get());
    }


    @PutMapping("/contatos/put/{id}")
    public ResponseEntity<Object> updateContact(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid ContatoRecordDto contatoRecordDto){

        Optional<ContatosModel> contato0 = contactsRepository.findById(id);
        if(contato0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não Encontrado.");

        }
        var contatosModel = contato0.get();
        BeanUtils.copyProperties(contatoRecordDto, contatosModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactsRepository.save(contatosModel));
    }

    @DeleteMapping("/contatos/delete/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable(value = "id")UUID id ){

        Optional<ContatosModel> contato0 = contactsRepository.findById(id);
        if(contato0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não encontrado.");

        }
        contactsRepository.delete(contato0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro de Contato deletado com sucesso.");
    }
}
