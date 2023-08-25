package br.com.rh.ferias_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,
        @NotNull
        LocalDate dataAdimissao

) {
}
