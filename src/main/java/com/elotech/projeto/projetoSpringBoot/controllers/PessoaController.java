package com.elotech.projeto.projetoSpringBoot.controllers;

import com.elotech.projeto.projetoSpringBoot.dtos.PessoaRecordDto;
import com.elotech.projeto.projetoSpringBoot.models.PessoaModel;
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
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;


    @PostMapping("/post")
    public ResponseEntity<PessoaModel> save(@ModelAttribute PessoaModel user,
                                            @RequestBody @Valid PessoaRecordDto pessoaRecordDto){
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaRecordDto, pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoaModel));
    }


    @GetMapping("/get")
    public ResponseEntity<List<PessoaModel>> getAllPeople(){
        List<PessoaModel> pessoaModelList = pessoaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOnePerson(@PathVariable(value = "id")UUID id){
        Optional<PessoaModel> person0 = pessoaRepository.findById(id);
        if(person0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não Encontrado.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(person0.get());
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value = "id")UUID id,
                                               @RequestBody @Valid PessoaRecordDto pessoaRecordDto){

        Optional<PessoaModel> person0 = pessoaRepository.findById(id);
        if(person0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não Encontrado.");

        }
        var personModel = person0.get();
        BeanUtils.copyProperties(pessoaRecordDto, personModel);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(personModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id")UUID id ){

        Optional<PessoaModel> person0 = pessoaRepository.findById(id);
        if(person0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não encontrado.");

        }
          pessoaRepository.delete(person0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro deletado com sucesso.");
    }




}
