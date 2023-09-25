package com.elotech.projeto.projetoSpringBoot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRecordDto(@NotBlank String name, @NotNull String cpf) {


}
