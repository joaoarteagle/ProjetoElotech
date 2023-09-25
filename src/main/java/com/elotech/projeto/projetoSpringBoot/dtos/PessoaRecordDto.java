package com.elotech.projeto.projetoSpringBoot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRecordDto(@NotBlank @NotNull String name,
                              @NotNull @NotBlank String cpf,
                              @NotNull @NotBlank String dataNasc) {


}
