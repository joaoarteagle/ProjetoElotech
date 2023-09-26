package com.elotech.projeto.projetoSpringBoot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoRecordDto(@NotBlank String name, @NotNull String fone, @NotNull @NotBlank String email) {


}
