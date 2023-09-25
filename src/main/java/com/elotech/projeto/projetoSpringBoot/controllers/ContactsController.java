package com.elotech.projeto.projetoSpringBoot.controllers;

import com.elotech.projeto.projetoSpringBoot.dtos.ContatoRecordDto;
import com.elotech.projeto.projetoSpringBoot.dtos.PessoaRecordDto;
import com.elotech.projeto.projetoSpringBoot.models.ContatosModel;
import com.elotech.projeto.projetoSpringBoot.models.PessoaModel;
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
@RequestMapping("/pessoas/contatos")
public class ContactsController {

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping("/post")
    public ResponseEntity<ContatosModel> save(
                                              @RequestBody @Valid ContatoRecordDto contatoRecordDto){

        var contatosModel =  new ContatosModel();
        BeanUtils.copyProperties(contatoRecordDto,contatosModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactsRepository.save(contatosModel));
    }



    @GetMapping("/get")
    public ResponseEntity<List<ContatosModel>> getAllContacts(){

        List<ContatosModel> contatosModelList = contactsRepository.findAll();

            return ResponseEntity.status(HttpStatus.OK).body(contatosModelList);



    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOneContact(@PathVariable(value = "id")UUID id){
        Optional<ContatosModel> contact0 = contactsRepository.findById(id);
        if(contact0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não Encontrado.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(contact0.get());
    }


    @PutMapping("/put/{id}")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable(value = "id")UUID id ){

        Optional<ContatosModel> contato0 = contactsRepository.findById(id);
        if(contato0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não encontrado.");

        }
        contactsRepository.delete(contato0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro de Contato deletado com sucesso.");
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<Object> postContact(@PathVariable (value = "id")UUID id,
                                                @RequestBody @Valid ContatoRecordDto contatoRecordDto){
        var contatosModel =  new ContatosModel();
        contatosModel.setPerson(pessoaRepository.getReferenceById(id));
        BeanUtils.copyProperties(contatoRecordDto,contatosModel);
        return ResponseEntity.status(HttpStatus.OK).body(contactsRepository.save(contatosModel));

    }
    @GetMapping("/get/{idPerson}")
    public ResponseEntity<Object> getContactPerson(@PathVariable(value = "idPerson")UUID id,
                                                   @RequestBody @Valid PessoaRecordDto pessoaRecordDto){

        var person = new PessoaModel(pessoaRepository.findById(id));
        person.getContacts();
        if(person.getContacts()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não Encontrado.");

        }
        List<ContatosModel> contactPerson = contactsRepository.findAll();
        if(contactPerson.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro de Contato não Encontrado.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(contactPerson.get());


    }
}
