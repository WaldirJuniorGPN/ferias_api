package br.com.rh.ferias_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLoja(
        @NotNull
        @NotBlank
        String nome
) {
}
